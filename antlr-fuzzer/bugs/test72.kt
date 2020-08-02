 
   #!    @file : out @file : private ( :: class { -> } ) typealias out = ( private ? ) ? ? ? object companion    fun sealed ( )  fun test ( cl :  expect Int . ( ) -> Int ) : Int = 11 . cl ( ) 
 
 class Foo { 
 val a = test { this } 
 } 
 
 fun box ( ) : String { 
 if ( Foo ( ) . a != 11 ) return " fail " 
 
 return " OK " 
 } 
 
 public class Data ( ) 
 
 public class Input ( val d : Data ) : Closeable { 
 public fun data ( ) : Int = 100 
 } 
 
 public class Output ( val d : Data ) : Closeable { 
  init { } public fun doOutput ( data : Int ) : Int = data 
 } 
 
 public interface Closeable { 
 open public fun close ( ) {  { 
 
  }  } 
 } 
 
 public fun Input . copyTo ( output : Output , size : Int ) : Long { 
 return output . doOutput ( this . data ( ) ) . toLong ( ) 
 } 
 