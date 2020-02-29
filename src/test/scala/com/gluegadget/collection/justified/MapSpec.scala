package com.gluegadget.collection.justified

import org.specs2.{ ScalaCheck, Specification }
import org.specs2.execute._, Typecheck._
import org.specs2.matcher.TypecheckMatchers._

import scala.collection.immutable.{ Map => M }

class MapSepc extends Specification with ScalaCheck {
  def is = s2"""
    justified Map's get should work like underlying Map's get         $jmget
    reusing a Key obtained from a different map should not type check $failTypecheckIncorrectKey"""

  def jmget = prop { (m: M[String, String]) =>
    val key       = m.keySet.headOption
    val originalV = key.map(m.get)

    val v = key.map { (k: String) =>
      Map.withMap(m) {
        case jm =>
          jm.member(k).map(jm.get)
      }
    }

    v must equalTo(originalV)
  }

  def failTypecheckIncorrectKey = typecheck {
    """
      Map.withMap(M("a"->"a")) { case m =>
        m.member("a") match {
          case Some(key) =>
            Map.withMap(M("b"->"b")) { case m2 =>
              m2.get(key)
            }
        }
      }
    """
  } must not succeed
}
