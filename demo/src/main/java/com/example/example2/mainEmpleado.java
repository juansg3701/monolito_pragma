package com.example.example2;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class mainEmpleado {
    public static void main(String[] args) {
        /*
        Informes informe = new Informes();
        JefeEmpleado instacia_empleado  = new JefeEmpleado(informe);
        Empleados instacia_secretaria  = new SecretariaEmpleado();

        System.out.println(instacia_empleado.getTareas());
        System.out.println(instacia_secretaria.getTareas());
         */
        ClassPathXmlApplicationContext  context = new ClassPathXmlApplicationContext("applicationContext.xml");

        Empleados Juan = context.getBean("miEmpleado",Empleados.class);
        SecretariaEmpleado Jose = context.getBean("miSecretaria",SecretariaEmpleado.class);
        System.out.println(Juan.getTareas());
        System.out.println(Juan.getInforme());

        System.out.println(Jose.getTareas());
        System.out.println(Jose.getInforme());

        System.out.println(Jose.getEmail());
        System.out.println(Jose.getNombreEmpresa());
        context.close();


    }
}
