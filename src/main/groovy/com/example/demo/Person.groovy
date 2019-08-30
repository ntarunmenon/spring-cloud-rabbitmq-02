package com.example.demo

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString
import groovy.transform.TupleConstructor


@ToString(includeNames = true)
@EqualsAndHashCode
@TupleConstructor
class Person {

    Person () {}

    String name
}
