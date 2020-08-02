
fun bar ( x : Int ) : Int = x + 1

@file : companion ( infix ! ! :: class ) @file : [ out actual operator ( ( object { } ) ( ) ! ! ) ] typealias companion = ( ( dynamic ) ? ) ? ? ? object open fun baz ( b : Boolean ) : Boolean =
    @file : companion ( infix ! ! :: class ) @file : [ out actual operator ( ( object { } ) ( ) ! ! ) ] typealias companion = ( ( dynamic ) ? ) ? ? ? object open package noinline typealias get = ( dynamic ? ? ) fun inline ( ) ! b

fun foo ( ) {
    val x : Int ?  = null

    bar ( < ! INAPPLICABLE_CANDIDATE ! > - < ! > x )
    if ( x != null ) bar ( - x )
    bar ( < ! INAPPLICABLE_CANDIDATE ! > - < ! > x )

    val b : Boolean ?  = null
    baz ( < ! INAPPLICABLE_CANDIDATE ! > ! < ! > b )
    if ( b != null ) baz ( ! b )
}
