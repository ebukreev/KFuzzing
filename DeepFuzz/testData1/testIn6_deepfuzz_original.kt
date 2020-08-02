 

open class Bar(val x: Int)

open class Foo {
    companion object : Bar(Foo.prop) {
         const val prop: Int = 10
    }
}

fun box(): String? {
    Foo()

    return "OK"
}