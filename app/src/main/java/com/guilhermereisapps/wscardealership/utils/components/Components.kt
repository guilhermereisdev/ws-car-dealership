package com.guilhermereisapps.wscardealership.utils.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Brush
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.LocalGasStation
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.SensorDoor
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.guilhermereisapps.wscardealership.R
import com.guilhermereisapps.wscardealership.data.model.Car
import com.guilhermereisapps.wscardealership.utils.navigation.ScreensNavigation
import java.text.NumberFormat
import java.util.Locale

@Preview
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(
    title: String = "Actual Screen",
    icon: ImageVector? = null,
    showProfile: Boolean = false,
    navController: NavController = rememberNavController(),
    onBackArrowClicked: () -> Unit = {}
) {
    val showMenu = remember { mutableStateOf(false) }

    TopAppBar(
        title = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                if (icon != null) {
                    Icon(
                        imageVector = icon,
                        contentDescription = "Arrow Back",
                        modifier = Modifier.clickable { onBackArrowClicked.invoke() },
                    )
                }
                Text(text = title)
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Yellow),
        actions = {
            IconButton(onClick = { showMenu.value = !showMenu.value }) {
                Icon(imageVector = Icons.Filled.MoreVert, contentDescription = "Menu")
            }
            DropdownMenu(expanded = showMenu.value, onDismissRequest = { showMenu.value = false }) {
                MenuItem(text = "Minha conta", showMenu = showMenu) {
                    navController.navigate(ScreensNavigation.UserOptions.name)
                }
                MenuItem(text = "Sair", showMenu = showMenu) {
//                    FirebaseAuth.getInstance().signOut().run {
//                        navController.navigate(ScreensNavigation.CarsForSaleScreen.name)
//                    }
                }
            }
        },
    )
}

@Preview
@Composable
fun MenuItem(
    text: String = "Menu",
    showMenu: MutableState<Boolean> = mutableStateOf(true),
    onClick: () -> Unit = {}
) {
    DropdownMenuItem(
        text = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = text)
            }
        },
        onClick = {
            showMenu.value = false
            onClick()
        },
    )
}

@Preview
@Composable
fun CarCard(
    car: Car = Car(
        ano = 2015,
        combustivel = "FLEX",
        cor = "BEGE",
        id = 1,
        modeloId = 12,
        modeloNome = "ONIX PLUS",
        numPortas = 4,
        timestampCadastro = 1696539488,
        valor = 50.000
    )
) {
    val formattedValue = remember {
        NumberFormat.getCurrencyInstance(Locale("pt", "BR")).format(car.valor?.times(1000))
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp
        )
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .background(Color.White)
        ) {
            Image(
                painter = painterResource(id = R.drawable.onixplus), // Substitua com uma imagem válida
                contentDescription = "Carro",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
                    .clip(RoundedCornerShape(16.dp))
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = car.modeloNome ?: "Modelo Desconhecido",
                style = MaterialTheme.typography.titleLarge.copy(
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                DetailItem(icon = Icons.Filled.CalendarMonth, label = car.ano?.toString() ?: "N/A")
                DetailItem(icon = Icons.Filled.LocalGasStation, label = car.combustivel ?: "N/A")
                DetailItem(icon = Icons.Filled.Brush, label = car.cor ?: "N/A")
                DetailItem(
                    icon = Icons.Filled.SensorDoor,
                    label = car.numPortas?.toString() ?: "N/A"
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = formattedValue,
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.Green
                )
            )
        }
    }
}

@Composable
fun DetailItem(icon: ImageVector, label: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Icon(icon, contentDescription = null, tint = Color.Gray)
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = label,
            style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold)
        )
    }
}