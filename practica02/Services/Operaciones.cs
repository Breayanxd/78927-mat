using System;
using WSDL.mensajes;

namespace WSDL.operaciones{
    public class Operaciones : Mensajes{
        private List<string> saludo = new List<string>();
        public string Saludar(string nombre){
            saludo.Add(nombre);
            return nombre;
        }

        public string Mostrar(int id){
            return saludo[id];
        }
    }
}