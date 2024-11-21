/*
 * Copyright © 2024 Priscila De Oliveira Galassini
 * Todos os direitos reservados.
 *
 * Este código é protegido por direitos autorais e não pode ser copiado, modificado ou redistribuído sem permissão expressa.
 * Se você tiver permissão para usar este código, ele é fornecido "no estado em que se encontra", sem garantia de qualquer tipo.
 */

package br.com.tecnology.galassini.rotadaf.functions

import androidx.appcompat.widget.AppCompatEditText


fun validateFields(vararg fields: AppCompatEditText, validator: (String) -> Boolean): Boolean {
    fields.forEach { field ->
        if (!validator(field.text.toString())) {
            return false
        }
    }
    return true
}