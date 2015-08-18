import java.io._
import java.awt.Color
import breeze.linalg._
import breeze.plot._

/**
 * @author Ruiwen
 */


object hello {
  def main(args: Array[String]): Unit = {
    print("hello")
    
    val basePath = "src/main/resources/KNN_Example_1.csv"
    val testData = getDataFromCSV(new File(basePath)) 
    
    val data = for (i <- 1 to 5) yield (i,i)
    
    val pointsX1 = new Array[Double](testData._1.length)
    for(i <- 0 until testData._1.length) {
      if(testData._2(i) == 1) {
        pointsX1(i) = testData._1(i)(0)
      }
    }
    
    val pointsY1 = new Array[Double](testData._1.length)
    for(i <- 0 until testData._1.length) {
      if(testData._2(i) == 1) {
        pointsY1(i) = testData._1(i)(1)
      }
    }
    
    
    val pointsX0 = new Array[Double](testData._1.length)
    for(i <- 0 until testData._1.length) {
      if(testData._2(i) == 0) {
        pointsX0(i) = testData._1(i)(0)
      }
    }
    
    val pointsY0 = new Array[Double](testData._1.length)
    for(i <- 0 until testData._1.length) {
      if(testData._2(i) == 0) {
        pointsY0(i) = testData._1(i)(1)
      }
    }

    val f = Figure()
    val p = f.subplot(0)
    p += plot(pointsX1, pointsY1, '.', "red")
    p += plot(pointsX0, pointsY0, '.', "blue")
    p.xlabel = "x axis"
    p.ylabel = "y axis"
    f.saveas("lines.png")
    
    
  }
  def getDataFromCSV(file: File): (Array[Array[Double]], Array[Int]) = {
    val source = scala.io.Source.fromFile(file)
    val data = source
        .getLines()
        .drop(1)
        .map(x => getDataFromString(x))
        .toArray

    source.close()
    val dataPoints = data.map(x => x._1)
    val classifierArray = data.map(x => x._2)

    

    return (dataPoints, classifierArray)        
  }

  def getDataFromString(dataString: String): (Array[Double], Int) = {

    //Split the comma separated value string into an array of strings
    val dataArray: Array[String] = dataString.split(',')

    //Extract the values from the strings
    val xCoordinate: Double = dataArray(0).toDouble
    val yCoordinate: Double = dataArray(1).toDouble
    val classifier: Int = dataArray(2).toInt

    //And return the result in a format that can later 
    //easily be used to feed to Smile
    return (Array(xCoordinate, yCoordinate), classifier)
  }
}