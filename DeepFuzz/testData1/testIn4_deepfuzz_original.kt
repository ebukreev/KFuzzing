 
 

 

 
public class JavaClass{
    public static <T> T id(T x) {
        return null;
    }
}

 

fun box(): String {
    val x = JavaClass.id(null)  

    return try {
        val a = if (x) {
            "NOK"
        } else "NOK"
        a
    } catch (e: java.lang.IllegalStateException) {
        "OK"
    }
}