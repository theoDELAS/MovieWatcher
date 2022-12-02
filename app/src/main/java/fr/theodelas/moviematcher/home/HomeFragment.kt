package fr.theodelas.moviematcher.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.constraintlayout.motion.widget.TransitionAdapter
import androidx.lifecycle.ViewModelProvider
import fr.theodelas.moviematcher.R
import kotlinx.android.synthetic.main.fragment_home.*
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import coil.load

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewModel = ViewModelProvider(this)[HomeViewModel::class.java]

        lifecycleScope.launchWhenCreated {
            viewModel.onViewCreated()
        }

        viewModel
            .modelStream
            .observe(viewLifecycleOwner, Observer {
                bindCard(it)
            })

        motionLayout.setTransitionListener(object : TransitionAdapter() {
            override fun onTransitionCompleted(motionLayout: MotionLayout, currentId: Int) {
                when (currentId) {
                    R.id.offScreenLike -> {
                        viewModel.didLikeMovie()
                    }
                }

                when (currentId) {
                    R.id.offScreenUnlike,
                    R.id.offScreenLike -> {
                        motionLayout.progress = 0f
                        motionLayout.setTransition(R.id.start, R.id.detail)
                        viewModel.swipe()
                    }
                }
            }
        })

        likeFloating.setOnClickListener {
            motionLayout.transitionToState(R.id.like)
            viewModel.didLikeMovie()
        }

        unlikeFloating.setOnClickListener {
            motionLayout.transitionToState(R.id.unlike)
        }
    }

    private fun bindCard(model: HomeModel) {
        model.cardTop?.title.let {
            name.text = it
        }
        model.cardTop?.description.let {
            description.text = it
        }

        imageView.load(model.cardTop?.imagePath ?: "https://via.placeholder.com/500") {
            crossfade(true)
        }
    }
}

data class HomeModel(
    val cardTop: HomeCardModel?,
    val cardBottom: HomeCardModel?
)

data class HomeCardModel(
    val title: String,
    val description: String,
    val releaseDate: String,
    val imagePath: String
)