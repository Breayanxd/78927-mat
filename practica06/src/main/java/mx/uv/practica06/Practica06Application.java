package mx.uv.practica06;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@SpringBootApplication
public class Practica06Application {
	
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
}
