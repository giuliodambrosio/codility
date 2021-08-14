
import org.specs2.execute.Result
import org.specs2.mutable.Spec

class TheCoderOfRiviaSpec extends Spec {
  "A Matrix3x3" should {
    "generate magic cubes by incrementing values in a minimum amount of changes" in generateMagicCubeTest
  }

  def generateMagicCubeTest: Result =
    Result.forall(testCases) {
      case (inputArray, validAnswers) =>
        val actualAnswer = TheCoderOfRivia.Solution.solution(inputArray)
        validAnswers.exists(_.sameElements(actualAnswer)) must beTrue
    }

  def testCases =
    Seq(
      Array(1, 4, 2, 1, 4, 5, 4, 4, 4) ->
        Set(
          Array(6, 4, 2, 2, 4, 6, 4, 4, 4)
        ),
      Array(3, 2, 0, 0, 0, 0, 0, 3, 2) ->
        Set(
          Array(3, 2, 0, 2, 0, 3, 0, 3, 2)
        ),
      Array(1, 1, 1, 2, 2, 1, 2, 2, 1) ->
        Set(
          Array(1, 1, 3, 2, 2, 1, 2, 2, 1)
        ),
      Array(0, 2, 3, 4, 1, 1, 1, 3, 1) ->
        Set(
          Array(1, 2, 3, 4, 1, 1, 1, 3, 2)
        ),
    )
}

