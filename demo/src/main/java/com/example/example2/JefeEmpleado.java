package com.example.example2;

public class JefeEmpleado implements Empleados{

    private CreacionInformes informeNuevo;

        public JefeEmpleado(CreacionInformes informeNuevo) {
            this.informeNuevo = informeNuevo;
        }

    public String getTareas(){
        return "Gestionar jefe";
    }

    @Override
    public String getInforme() {
        return "Informe creado por el Jefe " + this.informeNuevo.getInforme();
    }

}
