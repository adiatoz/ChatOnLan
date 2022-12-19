/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chatonlan;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;



public class Serverchat {
   
     

      static ArrayList<String> userNames = new ArrayList<String>();
      static ArrayList<String> admin = new ArrayList<String>();

      static ArrayList<PrintWriter> printWriters = new ArrayList<PrintWriter>();

      static int i;

      public static void main(String[] args) throws Exception{


           System.out.println("Waiting for clients...");

           ServerSocket ss = new ServerSocket(9806);

           while (true)

           {

             Socket soc = ss.accept();

               System.out.println("Connection established");

             ConversationHandler handler = new ConversationHandler(soc,ss);

             handler.start();

           }

           

      }
   
}

class ConversationHandler extends Thread

{
     static ServerSocket ss;
 
    Socket socket;

    BufferedReader in;

    PrintWriter out;

    String name;

 

   public ConversationHandler(Socket socket,ServerSocket ss) throws IOException {

        this.socket = socket;
         ConversationHandler.ss=ss;
    }

    @Override
   public void run()

   {

        try

        {

            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            out = new PrintWriter(socket.getOutputStream(), true);
           
           

            int count = 0;

            while (true)

            {

               if(count > 0)

               {

                    out.println("****#@");

               }

               else

               {

                    out.println("#$@^*#");

               }

           

               name = in.readLine();

           

               if (name == null)

               {
                        return;
                 
               }
           
       

               if(name.startsWith("#!!!!!!!!")){

                   if (!Serverchat.userNames.contains(name.substring(9)))

                   {
                   	
                       Serverchat.userNames.add(name.substring(9));
                       break;

                   }
               }else {
               	 if (!Serverchat.userNames.contains(name))

                    {
                    	
                        Serverchat.userNames.add(name);
                        break;

                    }
               }

             count++;

           }

           
            if(name.startsWith("#!!!!!!!!")){
                Serverchat.i++;
                out.println("$$$$$$##"+name.substring(9));
                Serverchat.admin.add(name.substring(9));
                out.println("$^&*(#$"+1);

            }else{
                out.println("$$$$$$##"+name);
            }
            Serverchat.printWriters.add(out);
           
            for (PrintWriter writer : Serverchat.printWriters) {
            	if(name.startsWith("#!!!!!!!!")) {
                    writer.println("$@#$%^&*"+ Serverchat.userNames);
                    writer.println(name.substring(9) +" has joined the chat");
                    writer.println("admincount"+Serverchat.i);}
    else {
    	 writer.println("$@#$%^&*"+ Serverchat.userNames);
         writer.println(name +" has joined the chat");
         writer.println("admincount"+Serverchat.i);
    }
                }

           

            while (true)

            {

                String message = in.readLine();

               

                if (message == null)

                {

                    return;

                }
         if(message.equals("shutdown")){
         for(PrintWriter writer : Serverchat.printWriters){
         writer.println("shutdownclients");
         }
         
         ss.close();
         
         }else if(message.startsWith("admincount")){
             
           
                     Serverchat.i=Integer.parseInt(message.substring(10, message.length()));
                     
         
         for (PrintWriter writer : Serverchat.printWriters) {

                    writer.println("admincount"+message.substring(10, message.length()));

                }
         
         
         }else if(message.startsWith("!@#$!@#$!@#")){
                    String s = message.substring(11,message.length());
                    System.out.println(s);
                    Serverchat.printWriters.remove(out);
                    System.out.print(Serverchat.userNames.toString());
                    if(Serverchat.admin.contains(name)){
                    Serverchat.admin.remove(name);
                    }
                    System.out.println("admiiiiii"+Serverchat.admin);
                    Serverchat.userNames.remove(name);
                     System.out.print(Serverchat.userNames.toString());
                     
                for (PrintWriter writer : Serverchat.printWriters) {

                    writer.println("$@#$%^&*"+ Serverchat.userNames);
                 if(name.startsWith("#!!!!!!!!")) {   if(s.equalsIgnoreCase("admin")){
                    writer.println(name.substring(9)+" was removed by the admin");
                    }else{
                    writer.println(name.substring(9)+" has left the chat");
                    }}
                 else {   if(s.equalsIgnoreCase("admin")){
                     writer.println(name+" was removed by the admin");
                     }else{
                     writer.println(name+" has left the chat");
                     }}
                }
               
                 
               
                }else if(message.startsWith("#%@#$@#$")){
               
                String s= message.substring(8, message.length());
                System.out.println(s);
                s=s.replace('[', ' ').replace(']',' ');
            System.out.println(s);
            String[] fans=s.split(",");
           
                  int [] arr = new int [fans.length];
      for(int i=0; i<fans.length; i++) {
        arr[i] = Integer.parseInt(fans[i].trim());
      }
     
      int[] fans1= new int[(arr.length)/2];
      int inndex=0;
      for(int i =0;i<arr.length-1;i++){
         
          if(arr[i]==arr[i+1]){
          fans1[inndex]=arr[i];
          inndex++;
          }  
                }
      for (int w : fans1) {
         
          if(!Serverchat.admin.contains(Serverchat.userNames.get(w))){

                    Serverchat.printWriters.get(w).println("#$@!%^&");
          }else{
          out.println("cannotremoveadmin");
          }
      }
     
               
               
                }else if(message.startsWith("^&*(^&*")){
                String s= message.substring(7, message.length());
                System.out.println(s);
                s=s.replace('[', ' ').replace(']',' ');
            System.out.println(s);
            String[] fans=s.split(",");
           
                  int [] arr = new int [fans.length];
      for(int i=0; i<fans.length; i++) {
        arr[i] = Integer.parseInt(fans[i].trim());
      }
     
      int[] fans1= new int[(arr.length)/2];
      int inndex=0;
      for(int i =0;i<arr.length-1;i++){
         
          if(arr[i]==arr[i+1]){
          fans1[inndex]=arr[i];
          inndex++;
          }  
                }
      Serverchat.i=Serverchat.i+fans1.length;
     
      for(int sw=0;sw<fans1.length;sw++){
         
          Serverchat.admin.add(Serverchat.userNames.get(fans1[sw]));
     
      }
      System.out.println("barrrrrrrr"+Serverchat.admin);
      for (int w : fans1) {

                    Serverchat.printWriters.get(w).println("$^&*(#$"+Serverchat.i);
      }
     
      for (PrintWriter writer : Serverchat.printWriters) {

                    writer.println("admincount"+Serverchat.i);

                }
     
     
                }else if(message.startsWith("$@#^&%^")){
               
                String kiss = message.substring(7, message.length()).replace('[', ' ').trim();
                System.out.println(kiss);
                       String[] op = kiss.split("]");
                       System.out.println(Arrays.toString(op));
               
               
           String[] q =op[0].split(",");
           System.out.println(q.length);
           System.out.println(Arrays.toString(q));
         
                int [] arr = new int [q.length];
      for(int i=0; i<q.length; i++) {
        arr[i] = Integer.parseInt(q[i].trim());
      }
     
      int[] fans= new int[(arr.length)/2];
      int inndex=0;
      for(int i =0;i<arr.length-1;i++){
         
          if(arr[i]==arr[i+1]){
          fans[inndex]=arr[i];
          inndex++;
          }
     
      }
       System.out.println(Arrays.toString(fans));
      if(name.startsWith("#!!!!!!!!")) { Serverchat.printWriters.get(Serverchat.userNames.indexOf(name.substring(9))).println("you send a private message  "+op[1]);
      }
      else {
    	  Serverchat.printWriters.get(Serverchat.userNames.indexOf(name)).println("you send a private message  "+op[1]);
      }
      for (int w : fans) {

                   if(name.startsWith("#!!!!!!!!")) { Serverchat.printWriters.get(w).println("private message by "+name.substring(9) + ": " + op[1]);
                   }else {Serverchat.printWriters.get(w).println("private message by "+name + ": " + op[1]);}
                   }
                }else{

                for (PrintWriter writer : Serverchat.printWriters) {

                   if(name.startsWith("#!!!!!!!!")) { writer.println(name.substring(9) + ": " + message);}
                   else { writer.println(name + ": " + message);}

                }
                }

            }

           

        }

        catch (IOException e)

        {

            System.out.println(e);

        }

   }

}
