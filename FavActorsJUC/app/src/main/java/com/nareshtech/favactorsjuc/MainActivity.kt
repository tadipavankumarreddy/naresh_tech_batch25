package com.nareshtech.favactorsjuc

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nareshtech.favactorsjuc.ui.theme.FavActorsJUCTheme

class MainActivity : ComponentActivity() {

    // TODO 1: Create a data class to structure your data
    data class Actors(
        val image: Int,
        val name: String,
        val yob: Int,
        val moviesCount: Int,
        val description: String,
        val wikipediaLink: String
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FavActorsJUCTheme {
                ActorsList(actors = prepareData())
            }
        }
    }

    @Composable
    private fun ActorsList(actors: List<Actors>) {
        // you can use LazyRow, LazyVerticalGrid, LazyVerticalStaggeredGrid, LazyHorizontalGrid
        LazyVerticalGrid(
            columns = GridCells.Fixed(2), // Display 2 columns
            modifier = Modifier
                .padding(WindowInsets.statusBars.asPaddingValues())
                .padding(WindowInsets.navigationBars.asPaddingValues())
        ) {
           items(actors){
               ActorItem(actor = it)
           }
        }
    }


    @Composable
    private fun ActorItem(actor: Actors) {
        Column(modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clickable {
                Toast.makeText(applicationContext, "Clicked on ${actor.name}", Toast.LENGTH_SHORT).show()
            }, horizontalAlignment = Alignment.CenterHorizontally) {

            // For Creating an Image
            Image(painter = painterResource(id = actor.image),
                contentDescription = "Actor",
                modifier = Modifier
                    .height(200.dp)
                    .width(150.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop)

            // For Extra Details
            /*Column(modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally) {*/

                Text(text = actor.name,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    color = colorResource(id = R.color.purple_700)
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(text = actor.yob.toString(),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    textAlign = TextAlign.Center,
                    color = colorResource(id = R.color.black)
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(text = actor.moviesCount.toString(),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                    textAlign = TextAlign.Center,
                    color = colorResource(id = R.color.black)
                )
            /*}*/
        }
    }
}

private fun prepareData() = mutableListOf(
    MainActivity.Actors(
        R.drawable.alluarjun,
        "Allu Arjun",
        1982,
        20,
        "Stylish Star Allu Arjun is an Indian actor known for his energetic dance moves and charismatic screen presence.",
        "https://en.wikipedia.org/wiki/Allu_Arjun"
    ),
    MainActivity.Actors(
        R.drawable.chiranjeevi,
        "Chiranjeevi",
        1955,
        150,
        "Megastar Chiranjeevi is a legendary Indian actor, producer, and politician, known for his powerful performances and philanthropic work.",
        "https://en.wikipedia.org/wiki/Chiranjeevi"
    ),
    MainActivity.Actors(
        R.drawable.amitabh,
        "Amitabh Bachchan",
        1942,
        200,
        "Amitabh Bachchan, often referred to as 'Big B', is a legendary Indian actor, known for his iconic voice, intense performances, and significant contributions to Indian cinema.",
        "https://en.wikipedia.org/wiki/Amitabh_Bachchan"
    ),
    MainActivity.Actors(
        R.drawable.kamal,
        "Kamal Hassan",
        1954,
        230,
        "Kamal Haasan is a versatile Indian actor, director, screenwriter, and producer, known for his experimental and challenging roles.",
        "https://en.wikipedia.org/wiki/Kamal_Haasan"
    ),
    MainActivity.Actors(
        R.drawable.rana,
        "Rana Daggubati",
        1984,
        30,
        "Rana Daggubati is an Indian actor known for his portrayal of powerful and intense characters.",
        "https://en.wikipedia.org/wiki/Rana_Daggubati"
    ),
    MainActivity.Actors(
        R.drawable.prabhas,
        "Prabhas",
        1979,
        25,
        "Prabhas is an Indian actor known for his role as Baahubali, a historical fantasy film that gained international recognition.",
        "https://en.wikipedia.org/wiki/Prabhas"
    ),
    MainActivity.Actors(
        R.drawable.ranbir,
        "Ranbir Kapoor",
        1982,
        25,
        "Ranbir Kapoor is an Indian actor known for his versatility and natural acting style.",
        "https://en.wikipedia.org/wiki/Ranbir_Kapoor"
    ),
    MainActivity.Actors(
        R.drawable.salman,
        "Salman Khan",
        1965,
        100,
        "Salman Khan is a popular Indian actor, producer, and television personality, known for his action and romantic roles.",
        "https://en.wikipedia.org/wiki/Salman_Khan"
    ),
    MainActivity.Actors(
        R.drawable.charan,
        "Ram Charan",
        1985,
        20,
        "Ram Charan is an Indian actor known for his stylish action sequences and charming personality.",
        "https://en.wikipedia.org/wiki/Ram_Charan"
    ),
    MainActivity.Actors(
        R.drawable.vijay,
        "Vijay",
        1975,
        65,
        "Vijay is a popular South Indian actor known for his mass appeal and energetic dance performances.",
        "https://en.wikipedia.org/wiki/Vijay_(actor)"
    )
)
