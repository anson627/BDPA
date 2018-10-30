package timeusage

import org.apache.spark.sql.{ColumnName, DataFrame, Row}
import org.apache.spark.sql.types.{
  DoubleType,
  StringType,
  StructField,
  StructType
}
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.{BeforeAndAfterAll, FunSuite}

import scala.util.Random

@RunWith(classOf[JUnitRunner])
class TimeUsageSuite extends FunSuite with BeforeAndAfterAll {
  test("timeUsage") {
    assert(TimeUsage.spark.sparkContext.appName === "Time Usage")
    assert(TimeUsage.spark.sparkContext.isStopped === false)
  }

  test("dfSchema"){
    val testSchema = TimeUsage.dfSchema(List("fieldA", "fieldB"))

    assert(testSchema.fields(0).name === "fieldA")
    assert(testSchema.fields(0).dataType === StringType)
    assert(testSchema.fields(1).name === "fieldB")
    assert(testSchema.fields(1).dataType === DoubleType)
  }

  test("row"){
    val testRow = TimeUsage.row(List("fieldA", "0.3", "1"))

    assert(testRow(0).getClass.getName === "java.lang.String")
    assert(testRow(1).getClass.getName === "java.lang.Double")
    assert(testRow(2).getClass.getName === "java.lang.Double")
  }

}
