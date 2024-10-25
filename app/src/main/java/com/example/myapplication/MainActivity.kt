package com.example.myapplication


import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.Role.Companion.Button
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity

class MyClass(
    private val intValue: Int,
    private val doubleValue: Double,
    private val stringValue: String,
    private val booleanValue: Boolean,
    private val charValue: Char
) {
    fun getIntValue(): Int {
        return intValue
    }

    fun getDoubleValue(): Double {
        return doubleValue
    }

    fun getStringValue(): String {
        return stringValue
    }

    fun isBooleanValue(): Boolean {
        return booleanValue
    }

    fun getCharValue(): Char {
        return charValue
    }
}

@Composable
fun MyClassPreview(myClass: MyClass) {
    val text = """
        Int Value: ${myClass.getIntValue()}
        Double Value: ${myClass.getDoubleValue()}
        String Value: ${myClass.getStringValue()}
        Boolean Value: ${myClass.isBooleanValue()}
        Char Value: ${myClass.getCharValue()}
    """.trimIndent()

    Surface(modifier = Modifier.padding(8.dp)) {
        Text(text = text)
    }
}

@Composable
fun CombinedPreview(cond1: (MyClass) -> Boolean, cond2: (MyClass) -> Boolean) {
    Column {
        if (cond1(MyClass(10, 3.14, "Hello", true, 'A')) || cond2(MyClass(10, 3.14, "Hello", true, 'A'))) {
            MyClassPreview(MyClass(10, 3.14, "Hello", true, 'A'))
        }
        if (cond1(MyClass(11, 4.32, "World", false, 'B')) || cond2(MyClass(11, 4.32, "World", false, 'B'))) {
            MyClassPreview(MyClass(11, 4.32, "World", false, 'B'))
        }
        if (cond1(MyClass(15, 6.98, "Ceva", true, 'B')) || cond2(MyClass(15, 6.98, "Ceva", true, 'B'))) {
            MyClassPreview(MyClass(15, 6.98, "Ceva", true, 'B'))
        }
        if (cond1(MyClass(17, 9.13, "Ceva2", false, 'T')) || cond2(MyClass(17, 9.13, "Ceva2", false, 'T'))) {
            MyClassPreview(MyClass(17, 9.13, "Ceva2", false, 'T'))
        }
        if (cond1(MyClass(19, 5.89, "Chef", true, 'P')) || cond2(MyClass(19, 5.89, "Chef", true, 'P'))) {
            MyClassPreview(MyClass(19, 5.89, "Chef", true, 'P'))
        }
        if (cond1(MyClass(20, 10.45, "Jocsaas", true, 'C')) || cond2(MyClass(20, 10.45, "Jocsaas", true, 'C'))) {
            MyClassPreview(MyClass(20, 10.45, "Jocsaas", true, 'C'))
        }
    }
}
@Composable
fun ClickableTextWithIntent() {
    val context =LocalContext.current

    ClickableText(
        text = AnnotatedString("Visit our website", spanStyle = SpanStyle(textDecoration = TextDecoration.Underline)),
        onClick = {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.wikipedia.org"))
            startActivity(context, intent, null)
        }
    )
}
@Preview(showBackground = true)
@Composable
fun CombinedPreviewWrapper() {
    var condition1 by remember { mutableStateOf("") }
    var condition2 by remember { mutableStateOf("") }

    Column {
        TextField(
            value = condition1,
            onValueChange = { condition1 = it },
            label = { Text("Condition 1") },
            modifier = Modifier.fillMaxWidth()
        )
        TextField(
            value = condition2,
            onValueChange = { condition2 = it },
            label = { Text("Condition 2") },
            modifier = Modifier.fillMaxWidth()
        )

        CombinedPreview(
            cond1 = { myClass ->
                myClass.getIntValue().toString() == condition1 ||
                        myClass.getDoubleValue().toString() == condition1 ||
                        myClass.getStringValue() == condition1 ||
                        myClass.isBooleanValue().toString() == condition1 ||
                        myClass.getCharValue().toString() == condition1
            },
            cond2 = { myClass ->
                myClass.getIntValue().toString() == condition2 ||
                        myClass.getDoubleValue().toString() == condition2 ||
                        myClass.getStringValue() == condition2 ||
                        myClass.isBooleanValue().toString() == condition2 ||
                        myClass.getCharValue().toString() == condition2
            }
        )
        ClickableTextWithIntent()

    }
}
