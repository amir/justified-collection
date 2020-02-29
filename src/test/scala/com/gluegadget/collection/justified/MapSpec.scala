package com.gluegadget.collection.justified

import org.specs2.{ScalaCheck, Specification}
import org.scalacheck.{Properties, Prop}
import scala.collection.immutable.{Map => M}

class MapSepc extends Specification with ScalaCheck {
  def is = s2"""
    justified Map's get should work like underlying Map's get $jmget"""

  def jmget = prop((m: M[String, String]) => {
    val key = m.keySet.headOption
    val originalV = key.map(m.get)

    val v = key.map { (k: String) =>
      Map.withMap(m) { case jm =>
        jm.member(k).map(jm.get)
      }
    }

    v must equalTo(originalV)
  })
}
