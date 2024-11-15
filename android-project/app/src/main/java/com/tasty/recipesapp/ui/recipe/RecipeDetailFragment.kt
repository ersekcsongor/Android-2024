package com.tasty.recipesapp.ui.recipe

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.MediaController
import android.widget.VideoView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.tasty.recipesapp.databinding.FragmentRecipeDetailBinding
import com.tasty.recipesapp.repository.RecipeRepository
import com.tasty.recipesapp.viewmodel.RecipeDetailViewModel
import com.tasty.recipesapp.viewmodel.RecipeDetailViewModelFactory

class RecipeDetailFragment : Fragment() {

    private var _binding: FragmentRecipeDetailBinding? = null
    private val binding get() = _binding!!

    private lateinit var recipeDetailViewModel: RecipeDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRecipeDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val repository = RecipeRepository(requireContext())
        recipeDetailViewModel = ViewModelProvider(this, RecipeDetailViewModelFactory(repository))
            .get(RecipeDetailViewModel::class.java)

        val recipeId = arguments?.getInt("recipeId") ?: return

        recipeDetailViewModel.fetchRecipeDetail(recipeId)
        recipeDetailViewModel.recipeDetail.observe(viewLifecycleOwner) { recipe ->
            recipe?.let {
                binding.recipeName.text = it.name
                binding.recipeDescription.text = it.description
                binding.recipeServings.text = "Servings: ${it.numServings}"
                binding.recipeCountry.text = "Country: ${it.country}"

                Glide.with(this).load(it.thumbnailUrl).into(binding.recipeThumbnail)

                val videoView: VideoView = binding.recipeVideoView
                val videoUrl = it.originalVideoUrl
                if (videoUrl.isNotEmpty()) {
                    val mediaController = MediaController(requireContext())
                    mediaController.setAnchorView(videoView)

                    videoView.setMediaController(mediaController)
                    videoView.setVideoPath(videoUrl)

                    videoView.start()
                }
            }
        }

        binding.backButton.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
