package com.example.example2;

public class SecretariaEmpleado implements Empleados {
    private CreacionInformes informeNuevo;
    private String nombreEmpresa;
    private String email;
    public String getTareas(){
        return "Secretaria";
    }

    @Override
    public String getInforme() {
        return "Informe creado por el secretario " + this.informeNuevo.getInforme();
    }

    public void setInforme(CreacionInformes informe) {
        this.informeNuevo = informe;
    }

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
