class A {
    var result = "Fail"

    var foo: String

    fun run(): String {
        class O {
            fun run() {
                foo = "OK"
            }
        }
        O().run()
        return foo
    }
}