package co.edu.umanizales.tads.model;


import co.edu.umanizales.tads.exception.ListException;
import lombok.Data;

import java.util.Random;


@Data
public class ListDECircular {
    private NodeDE head;
    private  int size;



    /*
    Método agregar lista circular
Reviso que la cabeza este vacía si lo esta
Agrego la nueva mascota a la cabeza
Y le digo a la cabeza que su nodo siguiente y su previo se ella misma
Si no está vacía entonces
Igualo mi temp a la cabeza
Recorro la lista hasta que siguiente de mi temp sea la cabeza
En el bucle reviso que no haya dos mascotas iguales y que no haya mascotas con el mismo nombre y locación en caso de haberlas lanzar una excepción
Cuando el bucle haya finalizado
Le digo a mi temp que su siguiente sea el nuevo nodo
Le digo a mi nueva mascota le digo que su nodo previo sea temp y que su siguiente sea cabeza
 Y a cabeza le digo que su previo sea el nuevo nodo
Al finalizar
Le sumo al tamaño de la lista

    **/




    public void add(Pet pet) throws ListException {
        if (head == null) {
            head = new NodeDE(pet);
            head.setNext(head);
            head.setPrevious(head);
        } else {
            NodeDE newNode = new NodeDE(pet);
            NodeDE temp = head;
            while (temp.getNext() != head) {
                if(temp.getData().getIdentification().equals(pet.getIdentification())){
                    throw new ListException("ERROR: La mascota  con identificación " + pet.getIdentification() +  " ya ha sido agregado.");
                }
                if(temp.getData().getName().equals(pet.getName()) && temp.getData().getLocation().equals(pet.getLocation())){
                    throw new ListException("ERROR: La mascota  con el nombre " + pet.getName() + " y la locación "  + pet.getLocation().getName() +  " ya ha sido agregado.");
                }



                temp = temp.getNext();
            }
            if(temp.getData().getIdentification().equals(pet.getIdentification())){
                throw new ListException("ERROR: La mascota  con identificación " + pet.getIdentification() +  " ya ha sido agregado.");
            }
            if(temp.getData().getName().equals(pet.getName()) && temp.getData().getLocation().equals(pet.getLocation())){
                throw new ListException("ERROR: La mascota  con el nombre " + pet.getName() + " y la locación "  + pet.getLocation().getName() +  " ya ha sido agregado.");
            }



            temp.setNext(newNode);
            newNode.setPrevious(temp);
            newNode.setNext(head);
            head.setPrevious(newNode);
        }

        size++;
    }

    /*Método imprimir
Creo  un método que devolverá un arreglo
Adentro del método cree un arreglo donde el tamaño del arreglo sea igual al tamaño de la lista
Creo una variable contador que inicie desde 0 para el arreglo
Reviso que la lista no este vacía en caso de estarlo lanzo una excepción
Después recorro la lista hasta que temp sea igual que la cabeza esto para asegurarme que entre el ultimo antes de la cabeza
 Para esto puedo utilizar un método do while el cual me permite entre por lo menos una vez en el bucle esto para que entre al bucle una vez cuando temp sea igual a cabeza
Adentro del bucle voy agregando las mascotas al arreglo con ayuda de mi contador
Al fina devuelvo el arreglo con las mascotas agregadas
*/



    public Pet[] print() throws ListException {
        Pet[] petList = new Pet[size];

        int num = 0;
        NodeDE temp = head;

        if (temp == null) {
            throw new ListException("ERROR: La lista está vacía");
        }

        do {
            petList[num] = temp.getData();
            temp = temp.getNext();
            num++;
        } while (temp != head );

        return petList;
    }

    /*Metodo valid
Igualo temp a la cabeza
Después bucle do while recorro toda la lista hasta que temp vuelva hacer igual que la cabeza  en búsqueda que no haya una mascota iguales y que no tengan el mismo nombre y locación
*/

    public void validAdd (Pet pet) throws  ListException{

            NodeDE temp = head;

            do  {

                if(temp.getData().getIdentification().equals(pet.getIdentification())){
                    throw new ListException("ERROR: La mascota  con identificación " + pet.getIdentification() +  " ya ha sido agregado.");
                }
                if(temp.getData().getName().equals(pet.getName()) && temp.getData().getLocation().equals(pet.getLocation())){
                    throw new ListException("ERROR: La mascota  con el nombre " + pet.getName() + " y la locación "  + pet.getLocation().getName() +  " ya ha sido agregado.");
                }

                temp = temp.getNext();

            }while (temp != head );

    }

    /*Método agregar al incio
Reviso que la cabeza este nula
Si lo esta llamo el método add para agregar la nueva mascota al inicio
Si no esta vacía entonces
Primero reviso que no se pueda agregar la mascota para eso uso el método valid
Luego igualo temp al elemento anterior de la cabeza
Y le digo a temp que su siguiente sea el nuevo nodo
Le digo a nueva mascota que su siguiente sea la cabeza y que su anterior temp y
Le digo a cabeza que su anterior sea nueva mascota
Y le digo a nueva mascota que ahora será la mascota
Y adentro de la condición le sumo 1 al tamaño de la lista
*/


    public void addToStart(Pet pet) throws ListException{

        if (head == null) {
           add(pet);
        }else{

            try {
                validAdd(pet);
            } catch (ListException e){
                throw new ListException(e.getMessage());
            }


            NodeDE newNode = new NodeDE(pet);
            NodeDE temp = head.getPrevious();
            temp.setNext(newNode);
            newNode.setPrevious(temp);
            newNode.setNext(head);
            head.setPrevious(newNode);
            head=newNode;
            size++;

        }

    }


    /*Bañar mascotas
Creo un método que recibirá un chart y devolverá un int
Hago que mi método no le importe si el char es mayúsculas o minúsculas
Creo e igualo temp a la cabeza
Reviso que la lista no este vacía en caso de estarlo lanzo una excepción
También reviso que el char que se agregué sea solamente f (foward ) o b  (back) en caso de no serlo lanzo una excepción
Luego selecciono un numero aleatorio que este entre el el tamaño de la lista asegurándome que también pueda tomar la cabeza y el ultimo numero de la lista
si el número es 1
Entonces es porque es a la cabeza que hay que limpiar así que no importa si es B o F entonces solo le cambio el valor del bolean de sucio verdadero a falso
Si no es hacia
Reviso si lo que usuario mando es F
Si es f entonces
Hago un bucle while que en cada giro pasara al siguiente temp y le sumara 1 aun contador y el bucle parara hasta que el contador sea igual al numero aleatorio
Cuando se pare el bucle
Tomo el temp que quedo y miro si esta sucio o no y si esta sucio le cambio bolean a false para decir que ya no esta sucio
Y si no esta sucio entonces lanzo una excepción diciendo que ya este bañado
Si no es f entonces
Hago lo mismo que hice con f pero con la diferencia que adentro del bucle while mi temp no ira hacia delante si no hacia atrás

Al final de vuelvo el numero aleatorio para poder en el controller decir que mascota limpie
*/

    public int  bathePets(char letter) throws ListException {
        char start = Character.toLowerCase(letter);
        NodeDE temp = head;

        if(temp==null) throw new ListException("ERROR: No hay perros para bañar ");

        if(start != 'b' && start != 'f'){
            throw new ListException("ERROR: tiene que ingresar f (forward) o B (back)");
        }


        Random rand = new Random();
        int num = rand.nextInt(size) + 1;
        if (num ==1 ){
            if (temp.getData().isDirty()){
                temp.getData().setDirty(false);
            }else {
                throw new ListException("La mascota de nombre: "+ temp.getData().getName() +"y de id  "+
                        temp.getData().getIdentification()+ " ya esta bañada ");

            }

        }else{
            int count = 1;
            if (start=='f'){


                while (count != num){

                    temp = temp.getNext();
                    count++;

                }

                if (temp.getData().isDirty()){
                    temp.getData().setDirty(false);
                }else {
                    throw new ListException("La mascota de nombre: "+ temp.getData().getName() +" y de id "+
                            temp.getData().getIdentification()+ " ya esta bañada ");

                }


            }else{
                while (count != num){

                    temp = temp.getPrevious();
                    count++;

                }

                if (temp.getData().isDirty()){
                    temp.getData().setDirty(false);
                }else {
                    throw new ListException("La mascota de nombre: "+ temp.getData().getName() +"y de id "+
                            temp.getData().getIdentification()+ " ya esta bañada ");

                }


            }




        }

            return num;


    }

    /*Limiar a  todos
Igualo el nodo a la cabeza
Reviso que si haya elementos en la lista si no lanzo una excepción
Luego recorro la lista con un do while para asegurarme que se limpie la cabeza y que tambien se bañe al ultima mascota
Adentro del bucle le cambio el valor de sucion True a false
 Y paso al siguiente
*/

    public void getCleanEveryone () throws ListException {

        NodeDE temp = head;

        if (temp == null) {
            throw new ListException("ERROR: La lista está vacía");
        }

        do {
            temp.getData().setDirty(false);
            temp = temp.getNext();
        } while (temp != head );


    }

    /*Ensuciar a  todos
Igualo el nodo a la cabeza
Reviso que si haya elementos en la lista si no lanzo una excepción
Luego recorro la lista con un do while para asegurarme que se ensucie la cabeza y que también se ensucié al ultima mascota
Adentro del bucle le cambio el valor de  false a True
 Y paso al siguiente
*/

    public void getDirtyEveryone () throws ListException {

        NodeDE temp = head;

        if (temp == null) {
            throw new ListException("ERROR: La lista está vacía");
        }

        do {
            temp.getData().setDirty(true);
            temp = temp.getNext();
        } while (temp != head );


    }




    /*Agregar en posición
Reviso que la posición no sea mayor a la lista o que haya elementos en la lista si no lanzo una excepción
Y compruebo que la mascota se pueda agregar con el método valid
Si la posición que se va agregar la nueva mascota es 1 entonces llamo el método agregar
Si no es uno entonces
Creo un contador
Y recorro la lista hasta que el contador no sea menor que la posición a agregar menos una posición
Adentro del bucle un Temp va pasando al siguiente y al contador se le suma 1
Cuando haya parado bucle
Le digo a mi nuevo mascota que agarre como siguiente al siguiente de Temp y le digo que agarre como anterior a Temp
y le digo al siguiente de temp que agarré como previo a la nueva mascota
y le digo temp que agarre como siguiente la nueva mascota
y adentro de la misma condición le sumo 1 al size de la lista
*/

    public void addInPosition(int position, Pet pet) throws ListException {
        if (size < position) {
            throw new ListException("ERROR: Ingreso una posición más grande que la lista.");
        }

        if (head == null) {
            throw new ListException("ERROR: La lista está vacía.");
        }

        try {
            validAdd(pet);
        } catch (ListException e){
            throw new ListException(e.getMessage());
        }


        if (position == 1) {
            addToStart(pet);
        } else {
            NodeDE temp = head;
            int count = 1;

            while (count < position -1) {
                temp = temp.getNext();
                count++;
            }


                NodeDE newNode = new NodeDE(pet);
                newNode.setNext(temp.getNext());
                newNode.setPrevious(temp);
                temp.getNext().setPrevious(newNode);
                temp.setNext(newNode);
             size++;
        }
    }




}






