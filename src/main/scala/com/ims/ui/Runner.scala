package com.ims.ui

import scala.swing.{Frame, SimpleSwingApplication}

object Runner extends SimpleSwingApplication {
//  val ui = new UI
//  ui.visible = true

  override def top: Frame = new UI
}
