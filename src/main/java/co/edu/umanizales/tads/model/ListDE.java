package co.edu.umanizales.tads.model;


import co.edu.umanizales.tads.controller.dto.ReportobjectsLocationSexDTO;
import co.edu.umanizales.tads.exception.ListSEException;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ListDE {
    private NodeDE head;
    private  int size;
    private  List<Pet> petList = new ArrayList<>();




    public void add(Pet pet) throws ListSEException {
        if (head == null) {
            head = new NodeDE(pet);
        } else {
            NodeDE newNode = new NodeDE(pet);
            NodeDE temp = head;
            while (temp.getNext() != null) {
                if(temp.getData().getIdentification().equals(pet.getIdentification())){
                    throw new ListSEException("ERROR: La mascota  con identificación " + pet.getIdentification() +  " ya ha sido agregado.");
                }
                if(temp.getData().getName().equals(pet.getName()) && temp.getData().getLocation().equals(pet.getLocation())){
                    throw new ListSEException("ERROR: La mascota  con el nombre " + pet.getName() + " y la locación "  + pet.getLocation().getName() +  " ya ha sido agregado.");
                }



                temp = temp.getNext();
            }
            if(temp.getData().getIdentification().equals(pet.getIdentification())){
                throw new ListSEException("ERROR: La mascota  con identificación " + pet.getIdentification() +  " ya ha sido agregado.");
            }
            if(temp.getData().getName().equals(pet.getName()) && temp.getData().getLocation().equals(pet.getLocation())){
                throw new ListSEException("ERROR: La mascota  con el nombre " + pet.getName() + " y la locación "  + pet.getLocation().getName() +  " ya ha sido agregado.");
            }

            temp.setNext(newNode);
            newNode.setPrevious(temp);
        }

        size++;
    }

    public List<Pet> print() throws ListSEException {
        petList.clear();

        NodeDE temp = head;
        if(temp==null) throw new ListSEException("ERROR: La lista esta vacía");

        while (temp != null) { // se cambia la condición para agregar también el último nodo
                petList.add(temp.getData());
                temp = temp.getNext();
            }

        return petList;
    }

    public void addToStart(Pet pet) {
        NodeDE newNode = new NodeDE(pet);
        if (head != null) {
            head.setPrevious(newNode); // Establecer el enlace anterior de la cabeza al nuevo nodo
            newNode.setNext(head);
        }
        head = newNode;
        size++;
    }



    public void deleteById(String code) throws ListSEException {
        if (head == null) throw new ListSEException("ERROR: La lista está vacía");
        // Verificar si el primer elemento coincide con el código a eliminar

        if (head.getData().getIdentification().equals(code)) {
            // Eliminar primer elemento
            head = head.getNext();
            if (head != null) head.setPrevious(null);
            size--;

            return;
        }
        int count = 0;
        NodeDE temp = head;
        while (temp != null) {
            if (temp.getData().getIdentification().equals(code)) {
                // Verificar si el elemento a eliminar tiene un nodo anterior
                if (temp.getPrevious() != null) {
                    temp.getPrevious().setNext(temp.getNext());
                }
                // Verificar si el elemento a eliminar tiene un nodo siguiente
                if (temp.getNext() != null) {
                    temp.getNext().setPrevious(temp.getPrevious());
                }
                size--;
                count++;

            }
            temp = temp.getNext();
        }
        if (count == 0) throw new ListSEException("ERROR: No hay ningún niño con el Id "+ code);
    }

   /* public void addInPosition(int position, Pet pet) throws ListSEException {
        if (size<position) throw  new ListSEException ("Ingreso uno posición mas grande que la lista  ");


        if (head==null) throw  new ListSEException ("La lista esta vaciá  ");

        if (position == 1) {
                addToStart(pet);
            } else {
                NodeDE temp = head;
                int cont =1;
                while (temp != null && cont<position-1)
                {

                    temp = temp.getNext();
                    cont++;
                }
                if (temp != null) {
                    NodeDE newNode = new NodeDE(pet);
                    newNode.setNext(temp.getNext());
                    newNode.setPrevious(temp);
                    if (temp.getNext() != null) {
                        temp.getNext().setPrevious(newNode);
                    }
                    temp.setNext(newNode);

                } else {
                    add(pet);
                }
            }

    }*/

    public void addInPosition(int position, Pet pet) throws ListSEException {
        if (size < position) {
            throw new ListSEException("ERROR: Ingreso una posición más grande que la lista.");
        }

        if (head == null) {
            throw new ListSEException("ERROR: La lista está vacía.");
        }

        if (position == 1) {
            addToStart(pet);
        } else {
            NodeDE temp = head;
            int cont = 1;
            while (temp != null && cont < position - 1) {

                temp = temp.getNext();
                cont++;
            }

            if (temp != null) {


                NodeDE newNode = new NodeDE(pet);
                newNode.setNext(temp.getNext());
                newNode.setPrevious(temp);
                if (temp.getNext() != null) {
                    temp.getNext().setPrevious(newNode);
                }
                temp.setNext(newNode);

            } else {
                add(pet);
            }
        }
    }


    public void invert () throws ListSEException {

        if (head == null || size==1) {
            throw new ListSEException("ERROR: La lista está vacía.");
        }
            ListDE tempList = new ListDE();
            NodeDE temp = head;
            while (temp != null) {
                tempList.addToStart(temp.getData());
                temp = temp.getNext();
            }
            head = tempList.getHead();

    }
    public void getfirstMale() throws ListSEException {

            ListDE tempList = new ListDE();
            NodeDE temp = head;
             if (head == null) {
            throw new ListSEException("ERROR: La lista está vacía.");
             }
             int count = 0;
              int countFamle = 0;
            while (temp != null) {
                if (temp.getData().getSex()=='M'){
                    tempList.addToStart(temp.getData());
                    count++;
                }else {
                    tempList.add(temp.getData());
                    countFamle ++;

                }
                temp = temp.getNext();

            }
        if  (count ==0 || countFamle ==0 ) throw new ListSEException("ERROR: solo hay niños de un genero");

        head = tempList.getHead();


    }

    public void intercalateBySex() throws ListSEException {
        ListDE tempBoysList = new ListDE();
        ListDE tempGirlsList = new ListDE();
        NodeDE temp = head;
        if(temp==null) throw new ListSEException("ERROR: La lista esta vacía");

        while (temp != null){
            if(temp.getData().getSex()=='M'){
                tempBoysList.add(temp.getData());
            }
            if(temp.getData().getSex()=='F'){
                tempGirlsList.add(temp.getData());
            }
            temp = temp.getNext();
        }

        ListDE intercalateList = new ListDE();
        NodeDE maleNode = tempBoysList.getHead();
        NodeDE femaleNode = tempGirlsList.getHead();
        if  (maleNode ==null || femaleNode ==null ) throw new ListSEException("ERROR: solo hay niños de un genero");
        while (maleNode != null || femaleNode != null){
            if (maleNode != null){
                intercalateList.add(maleNode.getData());
                maleNode = maleNode.getNext();
            }
            if (femaleNode != null){
                intercalateList.add(femaleNode.getData());
                femaleNode = femaleNode.getNext();
            }
        }
        head = intercalateList.getHead();
    }

    public void deletePetsByAge(byte age) throws ListSEException {
        ListDE tempList = new ListDE();
        ListDE tempList2 = new ListDE();

        NodeDE temp = head;
        if(temp==null) throw new ListSEException("ERROR: La lista esta vacía");


        while (temp != null) {
                if (temp.getData().getAge() != age) {
                    size=1;
                    tempList.add(temp.getData());
                    size++;

                }
                else {
                    tempList2.add(temp.getData());
                }


            temp = temp.getNext();

            }
        if  (tempList2.head==null) throw new ListSEException("ERROR: en la lista no hay ningún niño con esa edad ");

        head = tempList.getHead();


    }
    public float averageAge() throws ListSEException {

            NodeDE temp = head;
        if(temp==null) throw new ListSEException("ERROR: La lista esta vacía");

        int contador = 0;
            int ages = 0;
            while(temp != null) {
                contador++;
                ages = ages + temp.getData().getAge();
                temp = temp.getNext();
            }
            float average = 0;
            if (contador > 0) {
                average = ages / (float)contador;
            }
            return average;


    }


    public void advancePosition(String id, int motion) throws ListSEException {

            NodeDE temp = this.head;
         if(temp==null) throw new ListSEException("ERROR: La lista esta vacía");
        if(motion>size || motion<0) throw new ListSEException("ERROR: EL movimiento "+ motion + " no se puedo hacer");



        int count = 1;

            while (temp != null && !temp.getData().getIdentification().equals(id)) {

                temp = temp.getNext();
                count++;

            }

            if (temp != null) {
                int diferencia = count- motion;
                Pet petcopy = temp.getData();
                deleteById(temp.getData().getIdentification());
                if (diferencia > 0) {
                    addInPosition(diferencia, petcopy);}
                else{
                    addToStart(petcopy);
                }
            }

    }
    public void lostPosition (String id,int motion) throws ListSEException {

        NodeDE temp = this.head;
        if(temp==null) throw new ListSEException("ERROR: La lista esta vacía");
        if(motion>size || motion<0) throw new ListSEException("ERROR: EL movimiento "+ motion + " no se puedo hacer");

        int count = 1;

        while (temp != null && !temp.getData().getIdentification().equals(id)) {

                temp = temp.getNext();
                count++;

            }
            int sum = motion + count;
            if (temp != null) {
                Pet petCopy = temp.getData();
                deleteById(temp.getData().getIdentification());
                addInPosition(sum, petCopy);
            }

    }
    public void sendBottomByLetter(char letter) throws ListSEException {
            ListDE tempList = new ListDE();
            NodeDE temp = this.head;
            int count = 0;
        if(temp==null) throw new ListSEException("ERROR: La lista esta vacía");

        char upperFirstChar = Character.toUpperCase(letter);

            while(temp != null){
                char firstLetter = temp.getData().getName().charAt(0);
                if(Character.toUpperCase(firstLetter) != upperFirstChar )
                {
                    tempList.addToStart(temp.getData());
                }
                else{
                    tempList.add(temp.getData());
                    count++;
                }

                temp = temp.getNext();
            }
        if (count==0) throw new ListSEException("ERROR: No hay niños que su nombre inicie por la letra "+ letter);
        this.head = tempList.getHead();

    }

    public int getCountPetsByLocationCode(String code) throws ListSEException {
        int count = 0;
        NodeDE temp = this.head;
        if (temp == null) throw
                new ListSEException("ERROR: La lista esta vacía");

        while (temp != null) {
            if (temp.getData().getLocation().getCode().equals(code)) {
                count++;
            }
            temp = temp.getNext();
        }

        return count;
    }






    public int getCountPetsByLocationCodeLimited(String code) throws ListSEException {
        int count =0;
            NodeDE temp = this.head;
        if(temp==null) throw new ListSEException("ERROR: La lista esta vacía");

        while(temp != null){
                if(temp.getData().getLocation().getCode().substring(0, 5).equals(code)){
                    count++;
                }
                temp = temp.getNext();
            }

        return count;
    }



    public int rangeByAge(int min, int max) throws ListSEException {
        NodeDE temp = head;
        if(temp==null) throw new ListSEException("ERROR: La lista esta vacía");

        int counter = 0;
        while (temp !=  null) {
            if (temp.getData().getAge() >= min && temp.getData().getAge() <= max) {
                counter++;
            }
            temp= temp.getNext();
        }

        return counter;
    }


    public void getReportPetsByLocationGendersByAge(byte age, ReportobjectsLocationSexDTO report) throws ListSEException {
        if (head == null) {
            throw new ListSEException("ERROR: La lista está vacía");
        }
        if (age>15)  throw new ListSEException("ERROR: no puede haber niños de "+ age);

        NodeDE temp = this.head;
            while(temp!=null){
                if(temp.getData().getAge()>age){
                    report.updateQuantity(
                            temp.getData().getLocation().getName(),
                            temp.getData().getSex() );
                }
                temp = temp.getNext();
            }

    }


}






