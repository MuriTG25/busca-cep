package br.com.murilo.busca_cep.ui.extras

import android.content.Context
import android.widget.Toast

fun Context.mensagemDeAviso(
    texto: String,
    tempoMensagem: Int = Toast.LENGTH_SHORT,
) =
    Toast.makeText(
        this,
        texto,
        tempoMensagem,
    ).show()