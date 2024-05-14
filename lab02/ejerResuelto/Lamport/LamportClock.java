package Lamport;
import java.util.ArrayList;
import java.util.List;
public class LamportClock {
private int clock;
 //constructor que inicializa el reloj de lamport en cero
 public LamportClock() {
   this.clock = 0;
 }
 //metodo para avanzar el reloj
 public synchronized int tick() {
    this.clock++;
    return this.clock;
 }
 //metodo para actualizar el reloj despues de recibir un evento
 public synchronized void update(int receivedTime) {
   this.clock = Math.max(this.clock, receivedTime) + 1;
 }
 //metodo para obtener el tiempo actual del reloj
 public int getTime() {
    return this.clock;
 }
 //metodo para probar la funcionalidad del reloj
 public static void main(String[] args) {
   List<Thread> threads = new ArrayList<>();
   LamportClock clock = new LamportClock();
  //creacion de hilos para simular eventos concurrentes
   for (int i = 0; i < 5; i++) {
     Thread thread = new Thread(new Runnable() {
     @Override
     public void run() {
     //simular la creacion de un evento con un tiempo de lamport asociado
     int time = clock.tick();
System.out.println("Thread " + Thread.currentThread().getId() + " created event with Lamport time " + time);
 try {
   //simular una espera aleatoria entre la creacion y recepción del evento
 Thread.sleep((long) (Math.random() * 1000));
 } catch (InterruptedException e) {
 e.printStackTrace();
 }
  //simular la recepción del evento y actualizar el reloj 
int receivedTime = clock.tick();
System.out.println("Thread " + Thread.currentThread().getId() + " received event with Lamport time " + receivedTime);
 clock.update(receivedTime);
 }
});
 threads.add(thread);
 thread.start();
 }
  //esperar a que todos los hilos terminen su ejecución
 for (Thread thread : threads) {
  try {
 thread.join();
 } catch (InterruptedException e) {
 e.printStackTrace();
 }
 }
  //imprimir el tiempo final del reloj despues que todos los eventos hayan ocurrido
 System.out.println("Final Lamport time: " + clock.getTime());
 }
}
