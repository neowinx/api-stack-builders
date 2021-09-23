package controllers

import scala.language.postfixOps
import concurrent.Await

import javax.inject._
import play.api._
import play.api.mvc._
import play.api.libs.json._
import javax.inject.Inject

import scala.concurrent.Future
import scala.concurrent.duration._
import play.api.mvc._
import play.api.libs.ws._
import play.api.http.HttpEntity
import akka.actor.ActorSystem
import akka.stream.scaladsl._
import akka.util.ByteString

import scala.concurrent.ExecutionContext

import play.api.libs.concurrent.CustomExecutionContext

@Singleton
class HomeController @Inject()(implicit ec: ExecutionContext, ws: WSClient, val controllerComponents: ControllerComponents) extends BaseController {

  def index() =  Action.async { 
    val resp = ws.url("https://api.github.com/users/neowinx/repos").get()
    Future {
       val popo = Await.result(resp, 15 seconds)
       Ok(popo.json)
    }
  }

}
