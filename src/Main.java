import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.*;

public class Main {
/*
Урок 6. Работа с сетью
1. Написать консольный вариант клиент\серверного приложения, в котором пользователь может писать сообщения, как на клиентской стороне, так и на серверной.
Т.е. если на клиентской стороне написать "Привет", нажать Enter то сообщение должно передаться на сервер и там отпечататься в консоли.
     Если сделать то же самое на серверной стороне, сообщение соответственно передается клиенту и печатается у него в консоли.
     Есть одна особенность, которую нужно учитывать: клиент или сервер может написать несколько сообщений подряд, такую ситуацию необходимо корректно обработать
Разобраться с кодом с занятия, он является фундаментом проекта-чата
ВАЖНО! Сервер общается только с одним клиентом, т.е. не нужно запускать цикл, который будет ожидать второго/третьего/n-го клиентов
 */
    public static void main(String[] args)
    {
        int port  = 8290;
       // if (args.length == 0 )
       //  ;
        if(false) // else if (args[0] == "client")
        {
            try (Socket socket = new Socket("localhost", port)) {
                Response(socket, "Server");
            } catch (UnknownHostException e) {
                System.err.println("Don't know about host ");
                System.exit(1);
            } catch (IOException e) {
                System.err.println("Couldn't get I/O for the connection to ");
                System.exit(1);
            }
        }
        else  //if (args[0] == "server")
        {
            try (ServerSocket server = new ServerSocket(port)) {
                System.out.println("Сервер ...");
                try (Socket socket = server.accept()) {
                    System.out.println("--");
                    Response(socket, "Client");
                } catch (IOException e1) {
                    System.out.println(e1.getMessage());
                }
            } catch (IOException e2) {
                System.out.println(e2.getMessage());
            }
        }
    }

    public static void  Response(Socket socket, String Name) {
        try (Scanner scanner = new Scanner(System.in);
             DataOutputStream out = new DataOutputStream(socket.getOutputStream())) {
             DataInputStream in = new DataInputStream(socket.getInputStream());
             while (true) {

                    String msg = in.readUTF();
                     out.writeUTF(Name + ":" + msg);
                     out.flush();

                 if (msg.equals("bye")) {
                        System.out.printf("%s stop writing%n", Name);
                        break;
                    }
                    System.out.printf("%s says: %s%n", Name, msg);
            }
        } catch (IOException e) {
            System.out.println("writer stopped");
        }
    }
}
