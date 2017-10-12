package lambda

import com.amazonaws.services.lambda.runtime.{ Context, RequestHandler }


trait NotifyComponent extends RequestHandler[java.lang.Object, String] {

  val client: ScrapeClient
  val notifySlack: Notify
  val times = sys.env.getOrElse("TIMES", "1").toInt

  override def handleRequest(input: Object, context: Context): String = {
    for (_ <- 0 until times) {
      val result = doTask
      println(result)
      Thread.sleep(10000)
    }
    s"Times: $times"
  }

  def doTask: String = {
    if (client.toNotify) {
      notifySlack.notifySlack
    }
    println("siteText: " + client.siteText)
    client.siteText
  }
}

class NotifyController extends NotifyComponent {
  override val client: ScrapeClient = new ScrapeClient()
  override val notifySlack: Notify = new Notify()


}