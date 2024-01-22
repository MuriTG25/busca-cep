package br.com.murilo.busca_cep.ui.component.buscaCep

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import br.com.murilo.busca_cep.R

@Composable
fun ImagemComponent(
    modifier: Modifier = Modifier,
    imagemVector: Int = R.drawable.buscacepvector,
    descricaoImagem: String = "Logo do Aplicativo",
    contentScale: ContentScale = ContentScale.FillWidth

) {
    Image(
        modifier = modifier,
        painter = painterResource(id = imagemVector),
        contentDescription = descricaoImagem,
        contentScale = contentScale,
    )
}