package parttwo

import partone.Sample

/**
 * 请对类注释说明.
 *
 * @author wang_fei
 * @since 2022/4/25 15:02
 */
object Variable {

  def main(args: Array[String]): Unit = {

    var a = new Sample(name = "AAA", sl = 10);
    a = new Sample(name = "BBC", sl = 10);
    a.sl = 9;
    a.printinfo();
    println(a.field1);

    val b: Int = 8;
    println(b);
    var _b: Int = 8;
    _b= 4;
    println(_b);

    var +-*/%! = 5;
    println(+-*/%!);

    var c: String = if(b > _b) {
      "男"
    } else {
      "女"
    }
    print(c);
  }

}
