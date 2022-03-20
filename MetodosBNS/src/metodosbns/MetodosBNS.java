/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metodosbns;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import org.nfunk.jep.JEP;
/**
 *
 * @author equipo Jose Fernando, Marco Antonio, Yahir Ademar
 */
public class MetodosBNS {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
       
         MetodosBNS Proyecto = new MetodosBNS();
  Proyecto.menu();
    }
    
    //(1)Metodo Biseccion	
  public void MetodoBiseccion(){
    double a;
    double b;
    double tol;
    System.out.println("\t\t\t\"METODO DE BISECCION\"");
    System.out.println("Extremo Izquierdo: ");
    a=lee();
    System.out.println("Extremo Derecho: ");
    b=lee();
    System.out.println("Tolerancia: ");
    tol=lee();
    double c;
    do{
    c=(a+b)/2.0;
    if(((c*c-5)*(a*a-5))<0){
      b=c;
    }
    else{
      a=c;
    }
      }while(Math.abs(a-b)>tol);
      System.out.println("La raiz es: "+c);			
  }


  //(2)Metodo NewtonRaphson
  public void MetodoNewtonRaphson(){
    double a;
    double tol;
    double b;
    double c; 
    System.out.println("\t\t\t\"METODO DE NEWTON-RAPHSON\"");
    System.out.println("Primera Aproximacion: ");
    a=lee();
    System.out.println("Tolerancia: ");
    tol=lee();
    do{
      b=a-(a*a-a-2)/(2*a-1);
      c=Math.abs(a-b);
      a=b;	
      	}while(c>tol);
      	System.out.println("La raiz es: "+b);
    }

  public class Secante{
    public double raiz(Funcion f,double x0, double x1, int c,double e){
        double r = Double.NaN;
        double x2=x0;
        int k=0;
        while(Math.abs(f.eval(x2))>e&&k<c){
            x2=x0-f.eval(x0)*(x1-x0)/(f.eval(x1)-f.eval(x0));
            x0=x1;
            x1=x2;
            k++;
        }
        if(k<c){
            r=x2;
        }
        return r;//retornamos r que es el valor de la raiz
    }
}

public class Funcion {
    JEP j = new JEP();
    public Funcion(String def) {
        j.addVariable("x", 0);
        j.addStandardConstants();
        j.addStandardFunctions();
        j.parseExpression(def);

        if (j.hasError()) {
            System.out.println(j.getErrorInfo());
        }
    }
    public double eval(double x){
        double r;
        j.addVariable("x",x);
        r=j.getValue();
        if (j.hasError()) {
            System.out.println(j.getErrorInfo());
        }
        return r;
    }
}
  //para leer desde teclado
  public double lee(){
    double num;
    try{
      InputStreamReader isr = new InputStreamReader (System.in);
      BufferedReader br = new BufferedReader(isr);
      String sdato;
      sdato = br.readLine();
      num = Double.parseDouble(sdato);
    }
    catch(IOException ioe){
      num=0.0;
    }
    return num;
    }

  //para  leer un entero
  public int leeint(){
    int num;
    try{
      InputStreamReader isr = new InputStreamReader (System.in);
      BufferedReader br = new BufferedReader(isr);
      String sdato;
      sdato = br.readLine();
      num = Integer.parseInt(sdato);
    }
    catch(IOException ioe){
      num=0;
    }
    return num;
    }
  
  
  //para salir del programa
  public int Fuera(){
    int sal; 
    System.out.println("\n\n\nSI DESEAS OTRO METODO PRESIONA [1]");
    sal=leeint();
    return sal;
    }




  //despliega menu
  public void menu(){
    int a;
    int p;
    do{
    do{
    System.out.println("\n\n\t\t\tMETODOS NUMERICOS\n\n");
    System.out.println("\t1.-Biseccion\n\t2.-Newton-Raphson\n\t3.-Secante");
    System.out.println("\n\nEscoja el numero del metodo que desea usar:");
    a=leeint();
    }while(a<1 || a>8);
    switch(a){
      case 1:
      MetodoBiseccion();  //manda a llamara a cada uno de los metodos
      p=Fuera();
      break;
      case 2:
      MetodoNewtonRaphson();
      p=Fuera();
      break;
      case 3:
     // Secante.;
        String funcion;
        double valizq; double valder; int itermax;double e;
        Scanner teclado = new Scanner(System.in);
        System.out.print("Introduzca la funcion: ");
        funcion = teclado.nextLine();
      MetodosBNS.Funcion f =new MetodosBNS.Funcion (funcion);
      MetodosBNS.Secante s = new MetodosBNS.Secante();
      System.out.print("Introduzca valor incial izquierdo: ");
      valizq = teclado.nextDouble();
      System.out.print("Introduzca valor incial derecho: ");
      valder = teclado.nextDouble();
      System.out.print("Max Iteraciones ");
      itermax = teclado.nextInt();
      System.out.print("Error permitido ");
      e = teclado.nextDouble();
      System.out.print("La Raiz es: ");
      System.out.println(s.raiz(f, valizq, valder, itermax, e));
      
      p=Fuera();
      break;
      default:
      System.out.println("Opcion incorrecta");
      p=1;
      break;
      }
    }while(p==1);
    }
}
