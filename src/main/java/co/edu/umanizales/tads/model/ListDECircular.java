package co.edu.umanizales.tads.model;


import co.edu.umanizales.tads.exception.ListException;
import lombok.Data;

import java.util.Random;


@Data
public class ListDECircular {
    private NodeDE head;
    private  int size;




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
                do {
                    if (count == num){
                        if (temp.getData().isDirty()){
                            temp.getData().setDirty(false);
                        }else {
                            throw new ListException("La mascota de nombre: "+ temp.getData().getName() +"y de id "+
                                    temp.getData().getIdentification()+ " ya esta bañada ");

                        }

                        break;

                    }
                    temp = temp.getNext();
                    count++;
                } while (temp != head );
            }else{
                do{
                    if (count == num){
                        if (temp.getData().isDirty()){
                            temp.getData().setDirty(false);
                        }else {
                            throw new ListException("La mascota de nombre: "+ temp.getData().getName() +"y de id "+
                                    temp.getData().getIdentification()+ " ya esta bañada ");

                        }

                        break;

                    }
                    temp = temp.getPrevious()   ;
                    count++;
                } while (temp != head );

            }




        }

            return num;


    }

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




}






