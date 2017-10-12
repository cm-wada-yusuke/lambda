package lambda

import org.scalatest.{ FlatSpec, Matchers }

class NotifySpec extends FlatSpec with Matchers {
  "The Hello object" should "say hello" in {
    noException must be thrownBy new NotifyController().doTask
  }
}
