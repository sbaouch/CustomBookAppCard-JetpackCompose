package com.sbaouch.bookapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sbaouch.bookapp.ui.composables.BookGenre
import com.sbaouch.bookapp.ui.composables.BookInfoCard
import com.sbaouch.bookapp.ui.composables.CustomBookAppCard
import com.sbaouch.bookapp.ui.theme.BookAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BookAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color(0xFFF5F5F5)
                ) {
                    FavoriteBookListScreen()
                }
            }
        }
    }
}

@Composable
@Preview(device = Devices.PIXEL_4, backgroundColor = 0xFFFFFFFF, showBackground = true)
fun FavoriteBookListScreen() {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
    ) {
        CustomBookAppCard(
            bookInfoCard = BookInfoCard(
                coverImage = R.drawable.ic_sanderson_cover,
                bookTitle = "The stormlight archive 3",
                authorName = "Brandon Sanderson",
                genreList = listOf(
                    BookGenre(
                        genre = "Epic fantasy",
                        textColor = Color(0xFFCDAC56),
                        backgroundColor = Color(0xFFFCF5E1)
                    )
                ),
                totalPages = 1408,
                readPages = 0,
                favoriteBook = true,
                hasAnotations = false
            )
        ) {

        }

        Spacer(modifier = Modifier.height(10.dp))

        CustomBookAppCard(
            bookInfoCard = BookInfoCard(
                coverImage = R.drawable.ic_crz_cover,
                bookTitle = "The shadow of the wind",
                authorName = "Carlos Ruiz Zafón",
                genreList = listOf(
                    BookGenre(
                        genre = "Mystery",
                        textColor = Color(0xFFCD814B),
                        backgroundColor = Color(0xFFFBE9DD)
                    ), BookGenre(
                        genre = "Drama",
                        textColor = Color(0xFFAF84C5),
                        backgroundColor = Color(0xFFF8EAFF)
                    )
                ),
                totalPages = 512,
                readPages = 300,
                favoriteBook = true,
                hasAnotations = false
            )
        ) {

        }
    }
}