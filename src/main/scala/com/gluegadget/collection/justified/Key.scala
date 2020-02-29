package com.gluegadget.collection.justified

private[justified] class Key[PH, K] private (val k: K) extends AnyVal

private[justified] object Key {
  def apply[PH, K](k: K): Key[PH, K] = new Key(k)
}
