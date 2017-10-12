package lambda

import play.api.libs.json.Json

import scalaj.http.Http

class Notify {

  val webhooks = sys.env("WEBHOOK_URLS")
  val notifyBody = sys.env("NOTIFY_BODY")

  def notifySlack: Unit = webhooks.split(",").foreach(send)

  private def send(url: String): Unit = {
    val req = Http(url).postData("payload=" + reqBody)
    val res = req.asString
    println("[request] : " + req)
    println("[response] : " + res)
  }

  private def reqBody: String =
    Json.parse(s"""
      |{
      |  "username": "switch在庫",
      |  "text": "$notifyBody",
      |  "icon_emoji": ":ghost:",
      |  "link_names": 1
      |}
    """.stripMargin).toString


}
