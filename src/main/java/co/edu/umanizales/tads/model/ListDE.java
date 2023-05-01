package co.edu.umanizales.tads.model;


import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ListDE {
    private NodeDE head;
    private  int size;
    private  List<Pet> petList = new ArrayList<>();




    public void add(Pet pet) {
        if (head == null) {
            head = new NodeDE(pet);
        } else {
            NodeDE newNode = new NodeDE(pet);
            NodeDE temp = head;
            while (temp.getNext() != null) {
                temp = temp.getNext();
            }
            temp.setNext(newNode);
            newNode.setPrevious(temp);
        }

        size++;
    }

    public List<Pet> print() {
        petList.clear();
        if (head != null) {
            NodeDE temp = head;
            while (temp != null) { // se cambia la condición para agregar también el último nodo
                petList.add(temp.getData());
                temp = temp.getNext();
            }
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



    public void deleteById(String code) {
        if (head != null) { // Lista no está vacía
            if (head.getData().getIdentification().equals(code)) { // Eliminar primer elemento
                head = head.getNext();
                head.setPrevious(null);
                size--;
            }
            NodeDE temp = head;
            while (temp != null) {
                if (temp.getData().getIdentification().equals(code)) {
                    if (temp.getPrevious() != null) { // Verificar que tenga un nodo anterior
                        temp.getPrevious().setNext(temp.getNext());
                    }
                    if (temp.getNext() != null) {
                        temp.getNext().setPrevious(temp.getPrevious());
                    }
                    size--;
                }
                temp = temp.getNext();

            }

        }
    }

    public void addInPosition(int position, Pet pet) {

        if (head!=null) {
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
        }else{
            add(pet);
        }
    }

    public void invert (){
        if(head != null) {
            ListDE tempList = new ListDE();
            NodeDE temp = head;
            while (temp != null) {
                tempList.addToStart(temp.getData());
                temp = temp.getNext();
            }
            head = tempList.getHead();
        }
    }
    public void getfirstMale(){
        if(head != null) {
            ListDE tempList = new ListDE();
            NodeDE temp = head;
            while (temp != null) {
                if (temp.getData().getGender()=='M'){
                    tempList.addToStart(temp.getData());
                }else {
                    tempList.add(temp.getData());
                }
                temp = temp.getNext();
            }
            head = tempList.getHead();
        }

    }

    public void intercalateBySex(){
        ListDE tempBoysList = new ListDE();
        ListDE tempGirlsList = new ListDE();
        NodeDE temp = head;
        while (temp != null){
            if(temp.getData().getGender()=='M'){
                tempBoysList.add(temp.getData());
            }
            if(temp.getData().getGender()=='F'){
                tempGirlsList.add(temp.getData());
            }
            temp = temp.getNext();
        }

        ListDE intercalateList = new ListDE();
        NodeDE maleNode = tempBoysList.getHead();
        NodeDE femaleNode = tempGirlsList.getHead();
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

    public void deletePetsByAge(byte age) {
        if (head != null) {
            ListDE tempList = new ListDE();
            NodeDE temp = head;

            while (temp != null) {
                if (temp.getData().getAge() != age) {
                    size=1;
                    tempList.add(temp.getData());
                    size++;

                }

                temp = temp.getNext();

            }
            head = tempList.getHead();
        }

    }
    public float averageAge(){
        if (head != null){
            NodeDE temp = head;
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
        } else {
            return 0;
        }

    }


    public void advancePosition(String id, int motion, ListDE listDE) {
        if (head != null) {
            NodeDE temp = this.head;

            int count = 1;

            while (temp != null && !temp.getData().getIdentification().equals(id)) {

                temp = temp.getNext();
                count++;

            }

            if (temp != null) {
                int diferencia = count- motion;
                Pet petcopy = temp.getData();
                listDE.deleteById(temp.getData().getIdentification());
                if (diferencia > 0) {
                    listDE.addInPosition(diferencia, petcopy);}
                else{
                    addToStart(petcopy);
                }
            }
        }
    }
    public void lostPosition (String id,int motion, ListDE listDE){
        if (head != null) {
            NodeDE temp = this.head;

            int count = 1;

            while (temp != null && !temp.getData().getIdentification().equals(id)) {

                temp = temp.getNext();
                count++;

            }
            int sum = motion + count;
            if (temp != null) {
                Pet petCopy = temp.getData();
                listDE.deleteById(temp.getData().getIdentification());
                listDE.addInPosition(sum, petCopy);
            }
        }
    }
    public void sendBottomByLetter(char letter){
        if(this.head !=null){
            ListDE tempList = new ListDE();
            NodeDE temp = this.head;
            char upperFirstChar = Character.toUpperCase(letter);

            while(temp != null){
                char firstLetter = temp.getData().getName().charAt(0);
                if(Character.toUpperCase(firstLetter) != upperFirstChar )
                {
                    tempList.addToStart(temp.getData());
                }
                else{
                    tempList.add(temp.getData());
                }

                temp = temp.getNext();
            }
            this.head = tempList.getHead();
        }
    }

}






