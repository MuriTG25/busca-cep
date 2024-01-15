package br.com.murilo.busca_cep.ui.extras

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

class TransformadorDeCep:VisualTransformation{
    override fun filter(text: AnnotatedString): TransformedText {
        val cepMask = text.text.mapIndexed { index, c ->
            when (index) {
                4 -> "$c-"
                else -> "$c"
            }
        }.joinToString(separator = "")
        return TransformedText(AnnotatedString(cepMask), CepOffsetMapping)
    }

}

object CepOffsetMapping : OffsetMapping {
    override fun originalToTransformed(offset: Int): Int {
        return when{
            offset > 4 -> offset + 1
            else -> offset
        }
    }

    override fun transformedToOriginal(offset: Int): Int {
        return when{
            offset > 4 -> offset - 1
            else -> offset
        }
    }
}
