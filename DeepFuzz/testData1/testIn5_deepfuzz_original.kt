 

open class Bar(val x: Int)

open class Foo {
    companion object : Bar(Foo.x)
}

fun box(): String? {
    if (Foo() == null) return null

    return "OK"
}
