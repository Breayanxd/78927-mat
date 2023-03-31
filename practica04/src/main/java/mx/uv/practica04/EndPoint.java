package mx.uv.practica04;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;


import https.t4is_uv_mx.saludos.EliminarRequest;
import https.t4is_uv_mx.saludos.EliminarResponse;
import https.t4is_uv_mx.saludos.ModificarRequest;
import https.t4is_uv_mx.saludos.ModificarResponse;
import https.t4is_uv_mx.saludos.MostrarResponse;
import https.t4is_uv_mx.saludos.PedirRequest;
import https.t4is_uv_mx.saludos.PedirResponse;
import https.t4is_uv_mx.saludos.SaludarRequest;
import https.t4is_uv_mx.saludos.SaludarResponse;

@Endpoint
public class EndPoint {
    @Autowired
    private ISaludador iSaludador;

    ArrayList<String> nombres = new ArrayList<String>();
    int i=0;

    
    @PayloadRoot(localPart = "SaludarRequest", namespace="https://t4is.uv.mx/saludos")

    @ResponsePayload
    public SaludarResponse Saludar( @RequestPayload  SaludarRequest peticion) {
        SaludarResponse response= new SaludarResponse();
        response.setRespuesta("Hola "+peticion.getNombre());
        
        //persistencia a la bd
        Saludador saludador = new Saludador();
        saludador.setNombre(peticion.getNombre());
        iSaludador.save(saludador);
        return response;
    }

    @PayloadRoot(localPart = "MostrarRequest", namespace = "https://t4is.uv.mx/saludos")
    
    @ResponsePayload
    public MostrarResponse Mostrar() {
        MostrarResponse response = new MostrarResponse();
        String cad = "";
        for (Saludador saludador : iSaludador.findAll()) {
            String registro = "id: " +String.valueOf(saludador.getId())+" nombre: "+saludador.getNombre()+" ; ";
            cad += registro;
        }
        response.setRespuesta(cad);
        //persistencia a la bd
        return response;
    }


    @PayloadRoot(localPart = "ModificarRequest", namespace="https://t4is.uv.mx/saludos")

    @ResponsePayload
    public ModificarResponse Modificar( @RequestPayload  ModificarRequest peticion) {
        ModificarResponse response= new ModificarResponse();
        //BD
        if(iSaludador.findById(peticion.getId()).isPresent()){
            Saludador saludador = iSaludador.findById(peticion.getId()).get();
            saludador.setNombre(peticion.getNombre());
            response.setRespuesta("El nombre del id "+ peticion.getId() +" ha sido cambiado por "+peticion.getNombre());
            iSaludador.save(saludador);
        }else{
            response.setRespuesta("El registro con id "+ peticion.getId() +" no ha sido encontrado en la BD");    
        }
        return response;
    }

    @PayloadRoot(localPart = "PedirRequest", namespace="https://t4is.uv.mx/saludos")

    @ResponsePayload
    public PedirResponse Pedir(@RequestPayload PedirRequest peticion) {
        PedirResponse response = new PedirResponse();
        if(iSaludador.findById(peticion.getId()).isPresent()){
            Saludador saludador = iSaludador.findById(peticion.getId()).get();
            response.setRespuesta("Nombre: " + saludador.getNombre());
        }else{
            response.setRespuesta("El registro con id "+peticion.getId()+" no existe en la BD");
        }
        return response;
    }

    @PayloadRoot(localPart = "EliminarRequest", namespace="https://t4is.uv.mx/saludos")

    @ResponsePayload
    public EliminarResponse Eliminar(@RequestPayload EliminarRequest peticion) {
        EliminarResponse response= new EliminarResponse();
        if(iSaludador.findById(peticion.getId()).isPresent()){
            response.setRespuesta("El registro del id "+ peticion.getId() +" ha sido elimiando");
            iSaludador.deleteById(peticion.getId());
        }else{
            response.setRespuesta("El registro con id "+ peticion.getId() +" no ha sido encontrado en la BD");    
        }
        return response;
    }
}