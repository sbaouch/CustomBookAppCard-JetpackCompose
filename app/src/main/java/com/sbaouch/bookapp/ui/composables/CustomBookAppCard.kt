package com.sbaouch.bookapp.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sbaouch.bookapp.R
import com.sbaouch.bookapp.ui.theme.poppinsFont

data class BookGenre(
    val genre: String,
    val textColor: Color,
    val backgroundColor: Color
)

data class BookInfoCard(
    val coverImage: Int,
    val bookTitle: String,
    val authorName: String,
    val genreList: List<BookGenre>,
    val totalPages: Int,
    val readPages: Int,
    var favoriteBook: Boolean,
    val hasAnotations: Boolean
)

@Preview
@Composable
fun PreviewCard() {
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

@Composable
fun CustomBookAppCard(bookInfoCard: BookInfoCard, onClick: () -> Unit) {
    Surface(
        shape = RoundedCornerShape(12.dp),
        color = Color.White,
        modifier = Modifier
            .height(140.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {

            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(0.7f)
            ) {

                Image(
                    painter = painterResource(id = bookInfoCard.coverImage),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxHeight()
                )

            }

            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(2f)
            ) {

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End,
                ) {
                    Box(
                        contentAlignment = Alignment.TopEnd,
                        modifier = Modifier
                            .height(30.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .wrapContentSize()
                                .clip(RoundedCornerShape(bottomStart = 25.dp))
                                .background(Color(0xFFFFD584)),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                modifier = Modifier.padding(horizontal = 15.dp, vertical = 5.dp),
                                text = "${bookInfoCard.totalPages} pages",
                                textAlign = TextAlign.Center,
                                fontSize = 12.sp,
                                fontFamily = poppinsFont,
                                fontWeight = FontWeight.Medium
                            )
                        }
                    }
                }

                Row(
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(2f),
                ) {

                    Column(modifier = Modifier.weight(1f)) {
                        BookTitleAuthor(
                            bookTitle = bookInfoCard.bookTitle,
                            authorName = bookInfoCard.authorName,
                            genreList = bookInfoCard.genreList
                        )
                    }

                    Column(
                        modifier = Modifier.padding(top = 20.dp),
                        horizontalAlignment = Alignment.End,
                    ) {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier
                                .size(50.dp)
                                .clip(CircleShape)
                                .background(Color(0xFFF5F5F5))
                        ) {
                            Box(
                                contentAlignment = Alignment.Center,
                                modifier = Modifier
                                    .size(40.dp)
                                    .clip(CircleShape)
                                    .background(Color.White)
                                    .clickable {

                                    }
                            ) {
                                Icon(
                                    modifier = Modifier.size(25.dp),
                                    imageVector = Icons.Default.Favorite,
                                    contentDescription = "Heart",
                                    tint = if (bookInfoCard.favoriteBook) Color(0xFFFFA6A6) else Color(
                                        0xFFDEDEDE
                                    )
                                )
                            }
                        }

                        NumberOfPagesRead(bookInfoCard.totalPages, bookInfoCard.readPages)

                    }
                }

                LinearProgressIndicator(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(6.dp),
                    progress = bookInfoCard.readPages.toFloat() / bookInfoCard.totalPages.toFloat(),
                    color = Color(0xFFFFD584),
                    trackColor = Color(0xFFE5E5E5)
                )
            }
        }
    }
}


@Composable
fun BookGenreComposable(
    genre: String,
    textColor: Color,
    backgroundColor: Color
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = backgroundColor
        ),
        shape = RoundedCornerShape(5.dp),
    ) {
        Text(
            modifier = Modifier.padding(
                horizontal = 10.dp,
                vertical = 4.dp
            ),
            text = genre,
            color = textColor,
            fontFamily = poppinsFont,
            fontWeight = FontWeight.Normal,
            fontSize = 10.sp,
        )
    }

}

@Composable
fun NumberOfPagesRead(totalPages: Int, readPages: Int) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .height(78.dp)
            .padding(top = 10.dp, end = 4.dp)
    ) {
        Icon(
            modifier = Modifier
                .size(20.dp)
                .padding(end = 6.dp),
            painter = if (readPages != 0) painterResource(id = R.drawable.ic_book_open) else painterResource(
                id = R.drawable.ic_book_closed
            ),
            contentDescription = "Heart",
            tint = Color.Black
        )

        Text(
            text = "$readPages / $totalPages",
            textAlign = TextAlign.Center,
            fontSize = 12.sp,
            fontWeight = FontWeight.Light,
            style = MaterialTheme.typography.titleLarge
        )
    }
}


@Composable
fun BookTitleAuthor(bookTitle: String, authorName: String, genreList: List<BookGenre>) {
    Column(
        modifier = Modifier
            .padding(start = 5.dp),
        verticalArrangement = Arrangement.Center
    ) {

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = bookTitle,
            fontFamily = poppinsFont,
            fontWeight = FontWeight.Medium,
            fontSize = 14.sp
        )

        Spacer(modifier = Modifier.height(2.dp))

        Text(
            text = "by $authorName",
            color = Color(0xFF83869A),
            fontFamily = poppinsFont,
            fontWeight = FontWeight.Light,
            fontSize = 12.sp
        )

        Spacer(modifier = Modifier.height(4.dp))

        Row(
            verticalAlignment = Alignment.Bottom,
            modifier = Modifier
                .fillMaxHeight()
                .padding(bottom = 15.dp)
        ) {

            genreList.forEach { bookGenre ->
                BookGenreComposable(
                    genre = bookGenre.genre,
                    textColor = bookGenre.textColor,
                    backgroundColor = bookGenre.backgroundColor
                )
                Spacer(modifier = Modifier.width(8.dp))
            }
        }

    }
}