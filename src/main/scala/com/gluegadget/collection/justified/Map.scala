package com.gluegadget.collection.justified

import scala.collection.immutable.{ Map => M }

private[justified] class Map[PH, K, V] private (val m: M[K, V]) extends AnyVal {
  def member(k: K): Option[Key[PH, K]] = m.get(k).map(_ => Key(k))

  def get(key: Key[PH, K]): V = m(key.k)

  def keys: Iterable[Key[PH, K]] = m.keys.map(Key.apply)
}

object Map {
  def apply[PH, K, V](m: M[K, V]): Map[PH, K, V] = new Map(m)

  def withMap[K, V, T](m: M[K, V])(cont: (Map[PH, K, V] forSome { type PH }) => T): T = cont(Map(m))
}
