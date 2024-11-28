/*
 * Copyright © 2024 Priscila De Oliveira Galassini
 * Todos os direitos reservados.
 *
 * Este código é protegido por direitos autorais e não pode ser copiado, modificado ou redistribuído sem permissão expressa.
 * Se você tiver permissão para usar este código, ele é fornecido "no estado em que se encontra", sem garantia de qualquer tipo.
 */

package br.com.tecnology.galassini.rotadaf.activitys

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import br.com.tecnology.galassini.rotadaf.R

class CollectionsActivity : AppCompatActivity() {
    // Lista de igrejas
    private val churchList = mutableListOf("Assembléia De Deus", "Renascer Em Cristo", "Deus é Amor")
    private val churchSet = mutableSetOf<String>()
    // Mapa apenas com o nome das igrejas, sem preços
    private val churchMap = mutableMapOf("Assembléia De Deus" to "Assembléia De Deus",
        "Renascer Em Cristo" to "Renascer Em Cristo",
        "Deus é Amor" to "Deus é Amor")
    private var isAddingMode = true // Variável para alternar entre modos

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_collections)

        val listView = findViewById<ListView>(R.id.listView)
        val actionEditText = findViewById<EditText>(R.id.addItemEditText)
        val actionButton = findViewById<Button>(R.id.addItemButton)
        val toggleModeButton = findViewById<Button>(R.id.filterButton)
        val showSetButton = findViewById<Button>(R.id.showSetButton)
        val viewChurchesButton = findViewById<Button>(R.id.viewChurchButton)  // Botão para navegar para WidgetsActivity

        // Configurar ListView com adapter
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, churchList)
        listView.adapter = adapter

        // Alternar entre modos
        toggleModeButton.setOnClickListener {
            isAddingMode = !isAddingMode
            if (isAddingMode) {
                actionEditText.hint = "Adicionar igreja"
                actionButton.text = "Adicionar Igreja"
            } else {
                actionEditText.hint = "Digite uma letra para filtrar"
                actionButton.text = "Filtrar Igrejas"
            }
        }

        // Botão que funciona como "Adicionar" ou "Filtrar"
        actionButton.setOnClickListener {
            val input = actionEditText.text.toString().trim()
            if (isAddingMode) {
                // Adicionar igreja
                if (input.isNotEmpty()) {
                    churchList.add(input)
                    churchSet.add(input)
                    adapter.notifyDataSetChanged()
                    actionEditText.text.clear()
                    Toast.makeText(this, "$input adicionada!", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Digite uma igreja válida!", Toast.LENGTH_SHORT).show()
                }
            } else {
                // Filtrar igrejas
                if (input.length == 1) {
                    val filteredList = churchList.filter { it.startsWith(input, ignoreCase = true) }
                    if (filteredList.isNotEmpty()) {
                        adapter.clear()
                        adapter.addAll(filteredList)
                        adapter.notifyDataSetChanged()
                        // Ajuste para exibir a lista sem colchetes
                        val filteredMessage = "Igrejas filtradas pela letra '$input': ${filteredList.joinToString(", ")}"
                        Toast.makeText(this, filteredMessage, Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this, "Nenhuma igreja encontrada com a letra '$input'", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this, "Digite apenas uma letra!", Toast.LENGTH_SHORT).show()
                }
            }
        }

        // Mostrar conjunto de igrejas únicas
        showSetButton.setOnClickListener {
            val uniqueChurches = churchSet.joinToString(", ")
            if (uniqueChurches.isNotEmpty()) {
                Toast.makeText(this, "Igrejas únicas: $uniqueChurches", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Nenhuma igreja única encontrada!", Toast.LENGTH_SHORT).show()
            }
        }

        // Ao clicar no botão "Ver Igrejas", navegar para a WidgetsActivity
        viewChurchesButton.setOnClickListener {
            val intent = Intent(this, WidgetsActivity::class.java)
            startActivity(intent)
        }
    }
}
