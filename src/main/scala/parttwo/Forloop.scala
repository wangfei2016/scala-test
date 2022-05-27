package parttwo

/**
 * 请对类注释说明.
 *
 * @author wang_fei
 * @since 2022/4/25 17:14
 */
object Forloop {
  def main(args: Array[String]): Unit = {
    //for (i <- 0.to(10))
    for (i <- 0 to 10) {
      println(i + ".wasd")
    }
    println("************************")

    for (i <- 0 until 10) {
      println(i + ".wasd")
    }
    println("************************")

    for (i: Int <- Range(0, 10)) {
      println(i + ".wasd")
    }
  }

}
