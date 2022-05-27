package partone

/**
 * 请对类注释说明.
 *
 * @author wang_fei
 * @since 2022/4/25 14:43
 */
class Sample(name: String, var sl: Int) {
  val field1 = "666";
  var field2 = 888;
  def printinfo(): Unit = {
    println("name:" + name + "; sl:" + sl + "; bsdx:" + Sample.bsdx+ "; bsdx2:" + Sample2.bsdx);
  }
}

/**
 * Sample类的伴生对象
 */
object Sample {
  val bsdx: String = "是伴生对象属性";

  def main2(args: Array[String]): Unit = {
    val a = new Sample(name = "AAA", sl = 10);
    val b = new Sample(name = "BBB", sl = 20);
    a.printinfo();
    b.printinfo();
  }
}

  /**
   * Sample2为普通对象
   */
  object Sample2 {
    val bsdx: String = "是伴生对象属性2";
    def main(args:Array[String]): Unit = {
      val aa = new Sample(name = "AAA2", sl = 10);
      val bb = new Sample(name = "BBB3", sl = 20);
      aa.printinfo();
      bb.printinfo();
    }

}
