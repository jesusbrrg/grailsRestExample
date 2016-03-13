package restexample

import grails.transaction.*
import static org.springframework.http.HttpStatus.*
import static org.springframework.http.HttpMethod.*

@Transactional(readOnly = true)
class City2Controller {

   def index() {
	   def jsonMap = [:]
	   
	   jsonMap.put('City',City.list())
	   jsonMap.put('prueba','pruebaDatosJson')
	   respond jsonMap, model:[City:City.list(),prueba:"prueba"], view:'index2'
	  
	   
	   /**
	    * Problema!! hay que repetir elementos y se pierde la magia del render que no la permite el respond
	    * 
	    */
	   
	   
	   
   }
   
   
   def testMultipleObjectsRespond(){
	   
	   /**
	    * Para poder devolver m�ltiples objetos en el respond que corresponder�a al JSON, 
	    * creamos un mapa de elementos que permita enviarlos
	    * 
	    */
	   
	   def jsonMap = [:]
	   
	   jsonMap.put('City',City.list())
	   jsonMap.put('elemento1','elemento1')
	   
	   /**
	    * En este caso el JsonMap contiene m�s elementos que el render que se realizar�a si 
	    * tuviera una cabecera html
	    */
	   
	   respond jsonMap, model:['City':City.list()]
   }
   
   def adaptacionRest(){
		/**
		 * Con varios test de env�o de datos lo m�s factible para adaptaci�n es usando content negotiation con el elemento with format
		 * y adaptarnos a cualquier petici�n
		 * 
		 * 
		 * Para ello recordar que en el config.groovy hay que establecer:
		 * grails.mime.disable.accept.header.userAgents = ['None']
		 *    
		 * Para rest pruebas usar rest Console acordandose siempre de configurar el header para el content negotiation
		 *    
		 */
	   
	   def jsonMap = [:]
	   
	   jsonMap.put('City',City.list())
	   jsonMap.put('Prueba','PruebaCadena')
	  
	   
	   
	   withFormat{
		  html{ render(view:'index2', model:jsonMap)}
		  json{
			  respond jsonMap
		  }
		   
	   }
   }
   
   
}
