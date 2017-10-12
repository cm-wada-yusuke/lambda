package lambda

import net.ruippeixotog.scalascraper.browser.JsoupBrowser

import net.ruippeixotog.scalascraper.dsl.DSL._
import net.ruippeixotog.scalascraper.dsl.DSL.Extract._
import net.ruippeixotog.scalascraper.dsl.DSL.Parse._
import net.ruippeixotog.scalascraper.model._

class ScrapeClient {

  val url = sys.env("TARGET_URL")
  val checkString = sys.env("CHECK_STRING")
  val css = sys.env("TARGET_CSS")
  lazy val siteText = getText

  private def getText: String = {
    val browser = JsoupBrowser()
    val doc = browser.get(url)

    doc >> text(css)
  }

  def toNotify: Boolean = !(siteText == checkString)
}
