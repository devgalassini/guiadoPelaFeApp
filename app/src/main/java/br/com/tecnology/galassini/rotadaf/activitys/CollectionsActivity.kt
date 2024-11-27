package br.com.tecnology.galassini.rotadaf.activitys

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import br.com.tecnology.galassini.rotadaf.R

class CollectionsActivity : AppCompatActivity() {
    // Lista de igrejas
    private val churchList = mutableListOf("Igreja São Paulo", "Igreja Santo Antônio", "Igreja Nossa Senhora Aparecida")
    private val churchSet = mutableSetOf<String>()
    // Mapa apenas com o nome das igrejas, sem preços
    private val churchMap = mutableMapOf("Igreja São Paulo" to "Igreja São Paulo",
        "Igreja Santo Antônio" to "Igreja Santo Antônio",
        "Igreja Nossa Senhora Aparecida" to "Igreja Nossa Senhora Aparecida")
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
                        Toast.makeText(this, "Igrejas filtradas pela letra '$input': $filteredList", Toast.LENGTH_SHORT).show()
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
