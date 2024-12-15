import android.util.Log
import com.tasty.recipesapp.models.Recipe
import com.tasty.recipesapp.service.RecipeService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RecipeApiClient {
    companion object {
        private const val BASE_URL = "https://recipe-appservice-cthjbdfafnhfdtes.germanywestcentral-01.azurewebsites.net/"
    }

    private val recipeService: RecipeService

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        recipeService = retrofit.create(RecipeService::class.java)
    }

    suspend fun getRecipes(): List<Recipe>? {
        return withContext(Dispatchers.IO) {
            try {
                val response = recipeService.getRecipes()
                Log.d("RecipeApiClient", "Raw response: $response")
                response
            } catch (e: Exception) {
                Log.e("RecipeApiClient", "Error fetching recipes", e)
                null
            }
        }
    }

    suspend fun getRecipeById(recipeId: Int): Recipe? {
        return withContext(Dispatchers.IO) {
            try {
                val fullUrl = "${RecipeApiClient.BASE_URL}recipes/$recipeId"
                Log.d("RecipeApiClient", "Requesting URL: $fullUrl") // Log the full URL
                val recipe = recipeService.getRecipeById(recipeId)
                Log.d("RecipeApiClient", "Fetched recipe: $recipe")
                recipe
            } catch (e: HttpException) {
                Log.e("RecipeApiClient", "HTTP error: ${e.code()} - ${e.message()}", e)
                null
            } catch (e: Exception) {
                Log.e("RecipeApiClient", "Unknown error", e)
                null
            }
        }
    }





}
