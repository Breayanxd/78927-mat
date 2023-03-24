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

    @PayloadRoot(localPart = "ModificarRequest", namespace="https://t4is.uv.mx/saludos")

    @ResponsePayload
    public ModificarResponse Modificar( @RequestPayload  ModificarRequest peticion) {
        boolean band = false;
        ModificarResponse response= new ModificarResponse();
        for (int x = 0; x < nombres.size(); x++) {
            if (peticion.getNombreOld().equals(nombres.get(x))){
                nombres.set(x,peticion.getNombreNew());
                response.setRespuesta(peticion.getNombreOld()+" ha sido cambiado por "+nombres.get(x));
                band = true;
            }
        }
        if(!band){
            response.setRespuesta("Valor no encontrado");
        }
        return response;
    }

    @PayloadRoot(localPart = "PedirRequest", namespace="https://t4is.uv.mx/saludos")

    @ResponsePayload
    public PedirResponse Pedir() {
        PedirResponse response= new PedirResponse();
        String todos="";
        for (int x = 0; x < nombres.size(); x++) {
            todos=todos+ nombres.get(x) +", ";
        }
        response.setRespuesta(todos);
        return response;
    }

    @PayloadRoot(localPart = "EliminarRequest", namespace="https://t4is.uv.mx/saludos")

    @ResponsePayload
    public EliminarResponse Eliminar(@RequestPayload EliminarRequest peticion) {
        EliminarResponse response= new EliminarResponse();
        for (int x = 0; x < nombres.size(); x++) {
          if(peticion.getNombre().equals(nombres.get(x))){
            nombres.remove(x);
            response.setRespuesta(peticion.getNombre()+" eliminado con éxito");
          }
        }
        return response;
    }
}