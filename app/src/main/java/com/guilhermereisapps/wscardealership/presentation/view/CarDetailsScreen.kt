package com.guilhermereisapps.wscardealership.presentation.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.guilhermereisapps.wscardealership.R
import com.guilhermereisapps.wscardealership.data.model.Car
import com.guilhermereisapps.wscardealership.data.model.Compra
import com.guilhermereisapps.wscardealership.data.model.Comprador
import com.guilhermereisapps.wscardealership.presentation.components.AppBar
import com.guilhermereisapps.wscardealership.presentation.view.navigation.ScreensNavigation
import com.guilhermereisapps.wscardealership.presentation.viewmodel.CarsForSaleViewModel
import java.text.NumberFormat
import java.util.Locale

@Composable
fun CarDetailsScreen(
    navController: NavHostController,
    viewModel: CarsForSaleViewModel,
    car: Car
) {
    Scaffold(
        topBar = { AppBar(title = "Detalhes do carro") }
    ) { topBarPadding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(topBarPadding)
                .consumeWindowInsets(topBarPadding)
                .verticalScroll(rememberScrollState())
        ) {
            CarDetails(car = car, viewModel, navController)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CarDetails(car: Car, viewModel: CarsForSaleViewModel, navController: NavHostController) {
    var showModal by remember { mutableStateOf(false) }

    if (showModal) {
        ModalBottomSheet(
            onDismissRequest = { showModal = false },
            sheetState = rememberModalBottomSheetState(true)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Pronto. Um consultor de vendas entrará em contato em breve.",
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = {
                        showModal = false
                        navController.navigate(ScreensNavigation.CarsForSaleScreen.name) {
                            popUpTo(ScreensNavigation.CarsForSaleScreen.name) { inclusive = true }
                        }
                    }
                ) {
                    Text(text = "Entendi")
                }
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Image(
            painter = painterResource(id = car.image ?: R.drawable.nophoto),
            contentDescription = car.modeloNome,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .background(Color.Gray)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = car.modeloNome.toString(),
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Ano: ${car.ano}", style = MaterialTheme.typography.bodyLarge)
        Text(text = "Combustível: ${car.combustivel}", style = MaterialTheme.typography.bodyLarge)
        Text(text = "Cor: ${car.cor}", style = MaterialTheme.typography.bodyLarge)
        Text(text = "Portas: ${car.numPortas}", style = MaterialTheme.typography.bodyLarge)
        val formattedValue = remember {
            NumberFormat.getCurrencyInstance(Locale("pt", "BR")).format(car.valor?.times(1000))
        }
        Text(text = "Valor: $formattedValue", style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Se interessou? Solicite o contato de um revendedor!",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(8.dp))
        ContactForm(car, viewModel, onPurchaseComplete = { showModal = true })
    }
}

@Composable
fun ContactForm(
    car: Car,
    viewModel: CarsForSaleViewModel,
    onPurchaseComplete: () -> Unit,
) {
    val name = remember { mutableStateOf("") }
    val email = remember { mutableStateOf("") }
    val phone = remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        OutlinedTextField(
            value = name.value,
            onValueChange = { name.value = it },
            label = { Text("Nome") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Next
            )
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = email.value,
            onValueChange = { email.value = it },
            label = { Text("E-mail") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            )
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = phone.value,
            onValueChange = { phone.value = it },
            label = { Text("Telefone") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Phone,
                imeAction = ImeAction.Done
            )
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                val comprador = Comprador(
                    nome = name.value,
                    telefone = phone.value,
                    email = email.value
                )
                val compra = Compra(car = car, comprador = comprador)
                viewModel.insertCompra(compra)
                onPurchaseComplete()
            },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text(text = "Eu quero")
        }
    }
}
