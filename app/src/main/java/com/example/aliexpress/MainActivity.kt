package com.example.aliexpress

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.aliexpress.model.Grossist
import com.example.aliexpress.model.Product
import com.example.aliexpress.utils.AppLauncher
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var grossist: Grossist? = null
    var productList: MutableList<Product> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setOnClickListeners()

    }

    private fun setOnClickListeners() {
        add_grossist_button.setOnClickListener {

            val name = grossist_name_editText.text.toString()
            val tel = grossist_tel_editText.text.toString()
            val email = grossist_email_editText.text.toString()

            if (!name.isEmpty() && !tel.isEmpty() && !email.isEmpty()) {

                grossist = Grossist(
                        name,
                        tel,
                        email
                )

                grossist_layout.visibility = View.GONE
                product_list_layout.visibility = View.VISIBLE
                product_form_layout.visibility = View.VISIBLE
            }
        }

        add_product_button.setOnClickListener {
            addProductToList()
            printProductsToTextView()
        }

        order_products_button.setOnClickListener {

            grossist?.let {
                AppLauncher.sendSMS(it.tel, products_textView.text.toString(), this)
                //AppLauncher.sendEmail(it.email, "Produktbestilling", products_textView.text.toString(), this)
            }
        }
    }

    private fun addProductToList() {
        val name = product_name_editText.text.toString()
        val quantity = product_quantity_editText.text.toString().toInt()

        if (name.isNotEmpty() && quantity > 0) {
            productList.add(Product(name, quantity))
        }
    }

    private fun printProductsToTextView() {
        products_textView.text = ""

        for (product in productList) {
            products_textView.append("Navn: ${product.name} Antall: ${product.quantity} \n")
        }
    }
}