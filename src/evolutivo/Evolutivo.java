package evolutivo;

import java.util.Random;


        
public class Evolutivo {
    
    static int filas=6; 
    static int columnas=7;
    static int NGanadores=4; 
    static int Ngenes=3;
    
    
    
    static String [] [] Poblacion = new String [filas][columnas];
    static String [] [] PoblacionTem = new String [filas][columnas];
    static String [] parejas = new String [filas]; //[colum];
    static String []  Ganadores = new String [NGanadores];
   // static String [] ParejaA = new String [ParejaA];
    static double sumatoria=0;
    

    
/* Comienzo de la poblacion inicial de individuos */

public static void InicioPoblacion(String [][] Poblacion){

System.out.println("************************************************");
System.out.println("******************Inicio Poblacion ***************");
System.out.println("************************************************");
String individuo = " ";
Random ri = new Random();
for(int i=0; i<filas;i++){
    individuo = " ";
    for(int k=0;k<Ngenes;k++){
        
      individuo+=ri.nextInt(2)+",";
        
        
    } 
    Poblacion [i][0] =""+i;
    Poblacion [i][1] =individuo;
    
}
}
//Calculo del valor del individuo de acuerdo a sus genes 0 y 1

public static void Convertir_individuo(String[][] Poblacion){
    
    
    double valor=0;
    for(int i=0;i<filas; i++){
        
        valor=0;
        String [] valores=Poblacion[i][1].split(",");
        int indice=0;
        //cada valor se multiplica por potencia de 2
        for(int k=valores.length-1;k>=0;k--){
            
            
            valor=valor+ (Double.parseDouble(valores [k])*Math.pow(2,indice));
            indice++;
            
         }//for Interno
        
        Poblacion[i][2]=""+valor;
        //sumatoria+=valor;
        
    }
    
    
    
}


    /*Ecuacion que estudia la adaptabilidad del Individuo */
    
    public static double calidad_Cromosoma(String [][] Poblacion){
        
        //columna donde obtiene el valor del individuo
        
        double mayor=Double.parseDouble(Poblacion[0][2]);
        double valor=0;
        for (int i=0;i<filas;i++){
            
         //se evaluo con la funcion x al cuadrado 

         valor=function_FX(Double.parseDouble(Poblacion[i][2]));
           //private static double funcion_Fx(Double parseDouble) {
            
         //valor=funcion_Fx2(Double.parseDouble(Poblacion[i][2]));
         //se coloca en la columna 3
         Poblacion [i][3]=""+valor;
         sumatoria=+valor;
         // se busca rl mayor valor de todos , ese seria el mejor adaptado
         
         if(mayor<valor){
             
             mayor=valor;
             
             
            }
         
         }
            
          System.out.println("*******Mejor Adaptado*********");
          System.out.println("************"+ mayor+"************");
          //System.out.println("************************************************");
           
          return(mayor);
          
    }
         public static void Combinacion_Mutacion(String [][] Poblacion, String[][] PoblacionTem){
             
              System.out.println("*******Combinacion y Mutacion*********");
              System.out.println("************************************************");
             
             Random ri = new Random (); //aleatorio para la parte de la combinacion    
             
             int Cruce=0; 
             
             String [] individuoA;
             String [] individuoB;
             String ParejaA ;
             
             //se trabajar la mitad  
             for(int i=0;i<filas/2;i++){
                 
                 
                 individuoA=Poblacion [i][1].split(" , ");
                 ParejaA = parejas[i]; 
                 String CadAdn="";
                 individuoB=Poblacion[Integer.parseInt(ParejaA)][1].split(",");
                 Cruce=ri.nextInt(4); //punto de cruces 
                 System.out.println("Cruce ["+Cruce+"]["+Poblacion[i][0]+"]["+Poblacion[i][1]+"]"+ "[Cruzado con] ["+Poblacion[Integer.parseInt(ParejaA)][0]+"]"+"["+Poblacion[Integer.parseInt(ParejaA)][1]+"]");
                 //genes del Primer  individuo
                 for(int t=0;t<Cruce+1; t++){
                     
                     
                     CadAdn+=individuoA[t]+",";
                     }
                        //genes del segundo individuo
                        for(int t=0;t<individuoA.length;t++){
                            
                           CadAdn+=individuoB[t]+",";
                        
                        }
                
                 System.out.println("NUEVO individuo["+CadAdn+"]");
                 PoblacionTem[i][0]=""+i;
                 PoblacionTem[i][1]=CadAdn;
       
                 }//for de parejas
             for(int i=0; i<parejas.length;i++){
                 Poblacion [i][0]=PoblacionTem[i][0];
                 Poblacion [i][1]=PoblacionTem[i][1];  
                 
             }// se muta un gen despues de la combinacion
                 int mutado =(parejas.length/2)+1;
                 individuoA=Poblacion[mutado][1].split("   ");
                 System.out.println("****************Mutacion*************");
                 System.out.println("*********Cromosoma********Resultado**********");
                 int gen=ri.nextInt(4);// gen aleatorio
                 if(individuoA[gen].equals("0")){
                     
                    individuoA[gen]="1";
                 }else{
                            individuoA[gen]="0";
                            
                           }
                     
                       // se crea la cadena de ADN para mutar el gen
                       
                       String CadAdn="";
                       
                        for(int t=0;t<individuoA.length;t++){
                           CadAdn+=individuoA[t]+","; 
                            
                        }
                        //System.out.println("mutado --->"+CadAdn);
                        
                        System.out.println("["+Poblacion[mutado][0]+"]"+"["+Poblacion[mutado][1]+"]"+"Gen mutado"+" ["+gen+"] Resultado=>["+Poblacion[mutado][0]+"]"+"["+CadAdn+"]");
                        Poblacion[mutado][1]= CadAdn; // se adiciona el mutado a la poblacion
                        
           }//Combinacion_Mutacion
         
         //Los mejores Individuos pueden copiarse, sacar copia de si mismo
         public static void Duplicarse(String [][] Poblacion, String [][] PoblacionTem){
             
             
             System.out.println("********************Duplicarse************");
             
             int indice=0;
             int t=0;

             //se saca del vector de ganadores
             
             for(int i=0;i<Ganadores.length;i++){
                 if(Ganadores[i]!= null){
                 int ganador = Integer.parseInt(Ganadores[i]);
                 
                 PoblacionTem[indice][0]=""+(i+t); //nombre del Cromosoma
                 PoblacionTem[indice+1][0]=""+(i+1+t); // nombre del Cromosoma Duplicado
                 
                 //se duplica cada una de las columnas de poblacion a poblacion temporales
                 for(int f=2;f<columnas;f++){
                   PoblacionTem[indice][f]=Poblacion[ganador][f];
                   PoblacionTem[indice+1][f]=Poblacion[ganador][f];
                     }
                 indice+=2;
                 t++;
                 
                    }
                        //se pasa de la estructura temporal a la Principal
                        for(i=0; i<filas;i++){
                            
                            
                   PoblacionTem[i][0]=Poblacion[i][0];
                   PoblacionTem[i][1]=Poblacion[i][1];  
                            
                        }
             } 
         }      
                       
                         
             // aplicando un metodo para ver los ganadores de Cada Torneo
                 
                 
                
            public static void VerGanadores(String [] Ganadores){
             
                System.out.println("********************Ganadores*****************");
                	int gano=0;
                //for para ver los ganadores
                for(int i=0;i<Ganadores.length;i++){
                        if(Ganadores[i]!=null){
                        gano=Integer.parseInt(Ganadores[i]);
                    System.out.println("["+Poblacion[gano][0]+"]["+Poblacion[gano][1]+"]["+Poblacion[gano][2]+"]"+"["+Poblacion[gano][3]+"]");
                        }
                    
         }//for
                
               
         }//verGanadores  
            
            
           public static void Torneo(String [][] Poblacion) {
               
               System.out.println("********************Torneo*****************");
               String desempeñoA=""; String ParejaA="";    String desempeñoB="";
               int indP=0;
               
               for(int i=0;i<filas/2;i++){
                   
                 //Comportamiento de los dos cromosomas de acuerdo a la funcion actual
                 
                 desempeñoA=Poblacion [i][3];
                 ParejaA=parejas[i];
                 desempeñoB=Poblacion[Integer.parseInt(ParejaA)] [3];
                 System.out.println("["+Poblacion[i][0]+"] ["+Poblacion[i][1] +"] ["+Poblacion[i][2] +"] "+"["+desempeñoA+"] Contra ["+Poblacion[Integer.parseInt(ParejaA)] [0]  +"] "+"["+Poblacion[Integer.parseInt(ParejaA)] [1] +"] ["+Poblacion[Integer.parseInt(ParejaA)] [2] +"]"+"["+desempeñoB+"]");
                   // se comparan aqui
                   
                   if(Double.parseDouble(desempeñoA) >=Double.parseDouble(desempeñoB)){
          
                   
                  Ganadores[indP]=Poblacion[i][0];
                  
               }else{
                       
                      Ganadores[indP]=ParejaA; 
                           
                           indP++;
                        
               }
           
               }
           }
              //aqui es donde se puede realizar una rotacion
              public static void seleccion_Parejas(String [] [] Poblacion){
                  
                  System.out.println("*************************************");
                  System.out.println("********************Seleccion Parejas*****************");
                  String aux=Poblacion [1][0];
                  for(int i=0;i<filas;i++){
                
                      parejas[(filas-1)-i]=Poblacion[i][0];
                }//for
                  
                  
                  
               
           }//seleccion parejas
 
       
            //calculo de la probabilidad de la  adaptabilidad de cada Cromosoma   
              
              public static void Adaptabilidad(String [][] Poblacion, double sumatoria ){
                  
                  for(int i=0;i<parejas.length;i++){
                      
                      
                      Poblacion[i][4]=""+(Double.parseDouble(Poblacion[i][3])/sumatoria);
                      
                   }
                 
                  
             
                  }
                  //metodo que muestra la poblacion del Cromosoma y sus valores
                  
                  public static void verPoblacion(String [][] Poblacion,boolean pareja){
                      
                      System.out.println("*****************Poblacion Actual********************"); 
                      String Cadena="";
                   
                      
                   //hasta el numero de Cromosomas,filas
                   
                    for(int i=0; i<filas;i++){
                       
                       for(int k=0; k<columnas;k++){
                           
                        Cadena+="["+Poblacion[i][k]+"]"; 
                        
                        
                       }
                       
                       
                       //si se toma en cuenta con pareja o no
                       
                       
                       if(pareja)
                        
                            Cadena="Pareja"+parejas[i]+"\n";
                   
                       else 
                           Cadena+=""+"\n";
                       
                           }
                      System.out.println(Cadena);
                      
                      
              }
                  
                  //funcion de X elevado al Cuadrado
                 public static double function_FX(double X){
                      
                      return(X*X);
                      }
                  
                 public static double function_FX2(double X){
                      
                          return(X*X*X);
                      }
                  
                  //Metodo Principal
                  public static void main(String [] args){
                     InicioPoblacion(Poblacion);
                     double adaptados=0;
                     while(adaptados<961){
                     Convertir_individuo(Poblacion); 
                     adaptados=calidad_Cromosoma(Poblacion);
                     Adaptabilidad(Poblacion,sumatoria);
                     verPoblacion(Poblacion,true);
                     seleccion_Parejas(Poblacion);
                     Torneo(Poblacion);
                     VerGanadores(Ganadores);
                     Duplicarse(Poblacion,PoblacionTem);
                     verPoblacion(PoblacionTem,true);
                     seleccion_Parejas(Poblacion);   
                     Combinacion_Mutacion(Poblacion,PoblacionTem);
                     
              }
                     
                    adaptados=calidad_Cromosoma(Poblacion);  
                     verPoblacion(Poblacion,true);  
                      
                      
                  }

}
      
    


