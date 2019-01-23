package controllers

import play.api.libs.json._
import javax.inject._
import play.api.mvc.{Action, _}
import javax.inject.Inject
import play.core.Paths
import java.io.File

/**
  * This controller handles a file uploadpage.
  */
@Singleton
class HomeController @Inject()(cc: ControllerComponents) extends AbstractController(cc)  {

  /**
    * Create an Action to render an HTML page.
    *
    * The configuration in the `routes` file means that this method
    * will be called when the application receives a `GET` request with
    * a path of `/`.
    */

  def index = Action {
    Ok(views.html.index("SCRUTIN : Jugement Majoritaire"))
  }


  def action = Action {
    val list = services.Vote.result
    val json = Json.stringify( Json.obj(
      "list" -> JsArray( list.map( JsNumber(_) ) )
    ))
    Ok(views.html.action(json))
  }


  /*
  def upload = Action(parse.multipartFormData) { request =>
    request.body.file("fileUpload").map { video =>
      val videoFilename = video.filename
      val contentType = video.contentType.get
      video.ref.moveTo(new File("/home/nabil/IdeaProjects/JugMaj/public/data" + video.filename))
    }.getOrElse {
      Redirect(routes.HomeController.index)
    }
    Redirect(routes.HomeController.action)
  }

*/

}