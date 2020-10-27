package com.ims

import com.ims.ui.UI

import scala.swing.{Frame, SimpleSwingApplication}

object Runner extends SimpleSwingApplication {
  override def top: Frame = new UI
}
