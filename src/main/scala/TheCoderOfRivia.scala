import scala.annotation.tailrec

object TheCoderOfRivia {
  case class Matrix3x3(rows: Array[Array[Int]]) {

    rows.foreach(row => require(row.length == 3))

    lazy val rowSums: Array[Int] = rows.map(_.sum)
    lazy val colSums: Array[Int] = (0 to 2).map(col => (0 to 2).map(row => valueAt(row, col)).sum).toArray
    lazy val allSums: Set[Int] = colSums.toSet ++ rowSums
    lazy val isMagic: Boolean = allSums.size == 1
    lazy val goalValue: Int = allSums.max
    lazy val rowsToBeFixed: Map[Int, Array[Int]] = offendingSumToDiffAndIndices(rowSums)
    lazy val colsToBeFixed: Map[Int, Array[Int]] = offendingSumToDiffAndIndices(colSums)

    def toArray: Array[Int] = rows.flatten

    def move: Matrix3x3 = {
      if (isMagic)
        this
      else {
        val (rowDiff, rowIndices) = rowsToBeFixed.maxBy(_._1)
        val (colDiff, colIndices) = colsToBeFixed.maxBy(_._1)
        addToValueAt(rowIndices.head, colIndices.head, Math.min(rowDiff, colDiff))
      }
    }

    private def valueAt(row: Int, col: Int): Int = {
      require(row >= 0 && row < 3)
      require(col >= 0 && col < 3)
      rows(row)(col)
    }

    private def addToValueAt(valueRow: Int, valueCol: Int, valueDiff: Int): Matrix3x3= {
      val newRows = rows.zipWithIndex.map {
        case (row, rowIndex) =>
          row.zipWithIndex.map {
            case (previousValue, colIndex) if rowIndex == valueRow && colIndex == valueCol =>
              previousValue + valueDiff
            case (previousValue, _) =>
              previousValue
          }
      }
      Matrix3x3(newRows)
    }

    private def offendingSumToDiffAndIndices(sums: Array[Int]): Map[Int, Array[Int]] = {
      sums.zipWithIndex
        .filter(_._1 != goalValue)
        .map {
          case (sum, sumIndex) => (goalValue - sum) -> sumIndex
        }.groupBy(_._1)
        .map(kv => kv._1 -> kv._2.map(_._2))
    }
  }

  def solution(a: Array[Int]): Array[Int] = {
    val rows = Array(a.slice(0, 3), a.slice(3, 6), a.slice(6, 9))
    solution(Matrix3x3(rows)).toArray
  }

  @tailrec
  def solution(m: Matrix3x3): Matrix3x3 = {
    if (m.isMagic) {
      m
    } else {
      solution(m.move)
    }
  }

}