package mx.uv.practica03;

import java.util.ArrayList;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import org.springframework.xml.xpath.NodeMapper;

import https.t4is_uv_mx.saludos.EliminarRequest;
import https.t4is_uv_mx.saludos.EliminarResponse;
import https.t4is_uv_mx.saludos.ModificarRequest;
import https.t4is_uv_mx.saludos.ModificarResponse;
import https.t4is_uv_mx.saludos.PedirResponse;
import https.t4is_uv_mx.saludos.SaludarRequest;
import https.t4is_uv_mx.saludos.SaludarResponse;

@Endpoint
public class EndPoint {
    ArrayList<String> nombres = new ArrayList<String>();
    int i=0;

    @PayloadRoot(localPart = "SaludarRequest", namespace="https://t4is.uv.mx/saludos")

    @ResponsePayload
    public SaludarResponse Saludar( @RequestPayload  SaludarRequest peticion) {
        SaludarResponse response= new SaludarResponse();
        nombres.add(peticion.getNombre());
        i++;
        response.setRespuesta("Hola "+peticion.getNombre());
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
        String todos="He saludado a: ";
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