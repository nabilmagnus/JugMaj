package services

import controllers.routes

import scala.Array._
import scala.collection.mutable.{ListBuffer, Map}
import play.api.libs.json._
import play.api.libs.json.Json
import play.mvc.Controller


// Object Control
object Control {
  def using[A <: { def close(): Unit }, B](resource: A)(f: A => B): B =
    try {
      f(resource)
    } finally {
      resource.close()
    }
}


// Trait  pour lire les CSV
trait Csvreader {
  import java.io._

  import scala.io.Source

  def readFromFile(filename: String, separator: Char  = ';'):
  Option[Vector[Vector[String]]] = {
    try {
      Control.using(Source.fromFile(filename, "utf-8")) {
        source => Some(source.getLines.map(_.split(separator).toVector).toVector)
      }
    } catch {
      case e: FileNotFoundException => println("Couldn't find that file."); None
      case e: IOException => println("Got an IOException!"); None
      case e: Exception => print("Got an unlisted exception"); None
    }
  }
}





// Trait  pour Ecrire dans un CSV
trait Csvwriter {
  import java.io._

  def writeInFile(data: Vector[Vector[String]],
                  filename: String,
                  separator: String  = ";") {
    val bw = new BufferedWriter(new FileWriter(filename))
    data.foreach(v => bw.write(v.mkString(separator)))
    bw.close
  }
}



// Objet CSV data
object Csvdata extends Csvwriter with Csvreader




// Main de l'application
object Vote {

  def result: List[Double] = {

    var scrutin = new ListBuffer[List[String]]()
    val content = new Csvreader {}
    val result = content.readFromFile("/home/nabil/IdeaProjects/Jugement_Majoritaire/src/main/scala/data.csv")
    val s = result.toList(0)

    for (i <- 0 to (s.length - 2)) {
      var res = s(i).toList
      scrutin += res
    }

    val vote = Voter(scrutin)

    return vote

  }



  def winner() : String = {


    // Dictionaire des candidat

    var myList = result

    var mapCandidat = Map[Int, String]()
    mapCandidat(1) = " Candidat 1  "
    mapCandidat(2) = " Candidat 2 "
    mapCandidat(3) = " Candidat 3 "
    mapCandidat(4) = " Candidat 4 "
    mapCandidat(5) = " Candidat 5 "
    mapCandidat(6) = " Candidat 6 "


    var win = myList.last.toInt

    val winner = mapCandidat(win);

    return winner

  }




  def Voter(Vote: ListBuffer[List[String]]):List[Double] = {

    var mentionMaj: Int = 0;

    var rej: Double = 0;
    var insuff: Double = 0;
    var pass: Double = 0;
    var asbien: Double = 0;
    var bien: Double = 0;
    var trbien: Double = 0;
    var sum: Double = 0;

    var result = new ListBuffer[Int]()

    var matrix = ofDim[Double](6, 7)

    for (i <- 0 to (Vote(0).length - 1)) {

      var list =  Vote.map(Vote => Vote(i).toInt).toList

      rej = list.filter(_ == 1).size / 3.0
      insuff = list.filter(_ == 2).size / 3.0
      pass = list.filter(_ == 3).size / 3.0
      asbien = list.filter(_ == 4).size / 3.0
      bien = list.filter(_ == 5).size / 3.0
      trbien = list.filter(_ == 6).size / 3.0

      println("Resultat Candidat : " + (i + 1))
      println("Rejeter : " + rej + " - Insuffisant : " + insuff + " - Passable : " + pass + " - Assez Bien : " + asbien + " - Bien : " + bien + " - Tres bien : " + trbien)


      sum = rej

      if (sum >= 0.5) {

        mentionMaj = 1
        println("Mention majoritaire : { " + mentionMaj + " } ");
        result += mentionMaj

      } else if ((sum + insuff) >= 0.5) {

        mentionMaj = 2
        println("Mention majoritaire : { " + mentionMaj + " } ");
        result += mentionMaj

      } else if ((sum + insuff + pass) >= 0.5) {

        mentionMaj = 3
        println("Mention majoritaire : { " + mentionMaj + " } ");
        result += mentionMaj

      } else if ((sum + insuff + pass + asbien) >= 0.5) {

        mentionMaj = 4
        println("Mention majoritaire : { " + mentionMaj + " } ");
        result += mentionMaj

      } else if ((sum + insuff + pass + asbien + bien) >= 0.5) {

        mentionMaj = 5
        println("Mention majoritaire : { " + mentionMaj + " } ");
        result += mentionMaj

      } else if ((sum + insuff + pass + asbien + bien + trbien) >= 0.5) {

        mentionMaj = 6
        println("Mention majoritaire : { " + mentionMaj + " } ");
        result += mentionMaj

      }

      // build a matrix

      matrix(i)(0) = rej;
      matrix(i)(1) = insuff;
      matrix(i)(2) = pass;
      matrix(i)(3) = asbien;
      matrix(i)(4) = bien;
      matrix(i)(5) = trbien;
      matrix(i)(6) = mentionMaj;

      println("\n")

    }

    var sumer = new ListBuffer[Double]()

    val finale = result.toList

    val indexMax = finale.zipWithIndex.groupBy(_._1).maxBy(_._1)._2.map(_._2)

    println("\n")

    for (i <- 0 to 5) {

      for (j <- 0 to 6) {
        print(" " + matrix(i)(j));
      }

      println();
    }

    println("\n")

    val element = List()

    for (i <- 0 to indexMax.size - 1) {

      val l = indexMax(i)

      val mention = matrix(l)(6)

      for (j <- 0 to mention.toInt - 2) {

        print(" " + matrix(l)(j));

        sumer += matrix(l)(j)

      }

      println("\n");

      println();

    }


    val summer = sumer.toList

    val (left, right) = summer.splitAt(5)

    val sommeL = left.sum

    val sommeR = right.sum

    var winner: Int = 0;

    if (sommeL > sommeR) {

      winner = indexMax(0);

    } else {

      winner = indexMax(1);

    }

    var myList = matrix.flatten.toList;

    var win = myList :+ winner.toDouble

    return win

  }


}



