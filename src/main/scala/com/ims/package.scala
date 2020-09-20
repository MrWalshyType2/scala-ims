package com

import java.util.Scanner

package object ims {

  private val SCANNER = new Scanner(Console.in)

  def getInput(): String = {
    SCANNER.nextLine()
  }
}
