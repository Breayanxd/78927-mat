package mx.uv.practica06;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;




@RestController
@SpringBootApplication
public class Practica06Application {
	List<Personaje> listaPersonajes = new ArrayList<Personaje>();
	
	public Practica06Application(){
		listaPersonajes.add(new Personaje(0,"Bennet", "Mondstand", "Pyro"));
		listaPersonajes.add(new Personaje(1,"Itto", "Inazuma", "Geo"));
		listaPersonajes.add(new Personaje(2,"Fishl", "Mondstand", "Electro"));
	}
	public static void main(String[] args) {	
		SpringApplication.run(Practica06Application.class, args);
	}
	
	@RequestMapping("/hola")
	public String hola(){
		return "<h1>Hola Mundo</h1>";
	}

	@RequestMapping("/adios")
	public String adios(){
		return "<h1>Adios Mundo</h1>";
	}

	@RequestMapping("/")
	public String bienvenido(){
		return "<h1>Bienvenido</h1>";
	}

	@RequestMapping("/productos")
	public List<String> productos(){
		List<String> p = new ArrayList<String>();
		p.add("Tacos de Sal");
		p.add("Tamales");
		p.add("Carne");
		return p;
	}

	@RequestMapping("/productos2")
	public List<Productos> productos2(){
		List<Productos> lista = new ArrayList<Productos>();
		Productos p = new Productos("tacos",10);
		lista.add(p);
		Productos p1 = new Productos("pambazos",1);
		lista.add(p1);
		Productos p2 = new Productos("chescos",100);
		lista.add(p2);
		return lista;
	}

	@RequestMapping(value = "/saludar", method = RequestMethod.GET)
	public String saludarGet(){
		return "<h1>hola desde GET</h1>";
	}

	@RequestMapping(value = "/saludar", method = RequestMethod.POST)
	public String saludarPost(){
		return "<h1>hola desde POST</h1>";
	}
	@RequestMapping(value = "/personajes", method = RequestMethod.GET)
	public List<Personaje> getPersonajes() {
		return listaPersonajes;
	}

	@RequestMapping(value = "/personajes/{id}", method = RequestMethod.GET)
	public Personaje getPersonaje(@PathVariable int id) {
		Personaje response = listaPersonajes.get(id);
		return response;
	}

	@RequestMapping(value = "/personajes", method = RequestMethod.POST)
	public String postPersonaje(@RequestBody Personaje personaje){
		if(personaje != null){
			listaPersonajes.add(personaje);
			return "{\"message\":\"Success\"}";
		}else{
			return "{\"message\":\"Failed\"}";
		}
	}

	@RequestMapping(value = "/personajes/{id}", method = RequestMethod.DELETE)
	public String deletePersonaje(@PathVariable int id){
		listaPersonajes.remove(id);
		return "{\"message\":\"Success\"}";
	}

	@RequestMapping(value = "/personajes/{id}", method = RequestMethod.PUT)
	public String putPersonaje(@PathVariable int id, @RequestBody Personaje personaje){
		String response = "{\"message\":\"Failed\"}";
		Personaje pNew = new Personaje();
		pNew.setId(personaje.getId());
		pNew.setNombre(personaje.getNombre());
		pNew.setCiudad(personaje.getCiudad());
		pNew.setElemento(personaje.getElemento());
		if(listaPersonajes.contains(personaje)){
			listaPersonajes.set(id, pNew);
			response = "{\"message\":\"Success\"}";
		}
		return response;
	}
}
