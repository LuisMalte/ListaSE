package co.edu.umanizales.tads.model;

import lombok.Data;


@Data
public class ListSE {
    private Node head;
    private int size=0;


    /*
    Algoritmo de adicionar al final
    Entrada
        un niño
    si hay datos
    si
        llamo a un ayudante y le digo que se posicione en la cabeza
        mientras en el brazo exista algo
            pasese al siguiente
        va estar ubicado en el ùltimo

        meto al niño en un costal (nuevo costal)
        y le digo al ultimo que tome el nuevo costal
    no
        metemos el niño en el costal y ese costal es la cabeza
     */
    public void add(Kid kid) {

        if (head != null) {
            Node temp = head;
            while (temp.getNext() != null) {
                temp = temp.getNext();
            }
            /// Parado en el último
            Node newNode = new Node(kid);
            temp.setNext(newNode);
        } else {
            head = new Node(kid);
        }
        size ++;

    }

    /* Adicionar al inicio
    si hay datos
    si
        meto al niño en un costal (nuevocostal)
        le digo a nuevo costal que tome con su brazo a la cabeza
        cabeza es igual a nuevo costal
    no
        meto el niño en un costal y lo asigno a la cabez
     */


    public void addToStart(Kid kid) {
        if (head != null) {
            Node newNode = new Node(kid);
            newNode.setNext(head);
            head = newNode;
        } else {
            head = new Node(kid);
        }
    }


    /*algoritmo para adicionar en posicion
    primero saber el tamaño de la lista con un metodo que recorra  la lista hasta que no haya un niño siguiente y en cada vuelta del bucle se va contando
    luego se crea un meotdo donde recive el niño nuevo y en donde se quiere ser agregado
    si la posicion que se quiere es coherente con el tamaño de la lista
      entonces
        se crea el niño
         si la posicion donde se quiere adicionar es 0  lo que sera la cabeza entonces
            entonces  se llamara el metodo que agrega el niño al inicio
         en caso de que sea otra posicion diferente a 0
            entonces se crea un mensajero para que vaya una posicion antes de la pedida
                cuando se llegue a a la posicion entonces
                    se crea un nuevo nodo
                    y el siguiente niño del nuevo nodo sera lo siguiente que teniea el menesajero
                    y lo siguiente del mensjaero sera el nuevo nodo

        en caso de que la posicion no sea posible se agrega al final con un metood agregar

    * */


    public void addInPosition(int position, Kid kid) {

        if (head!=null) {
            if (position == 1) {
                addToStart(kid);
            } else {
                Node temp = head;
                int cont =1;
                while (temp != null && cont<position-1)
                {
                    temp = temp.getNext();
                    cont++;
                }
                    if (temp != null) {
                    Node newNode = new Node(kid);
                    newNode.setNext(temp.getNext());
                    temp.setNext(newNode);

                } else {
                    add(kid);
                }
            }
        }else{
            add(kid);
        }
    }
    /*
     * algoritmo para eliminar por id
     * se crea una mensajero que sea igual a la cabeza
     * y otro nodo el cual sera para indentificar al anterior nodo
     * lugo se recorre la lista solo en caso de que el mensajer tenga algun valor y en caso de no se haya encontrado el niño con ese id
     * a dentro del bucle el segudno nodo sera igual al mensajero y el mensajero para a otra niño asi hasta que termine el ciclo
     * en caso de que el mensajero no haya quedado vacio
     * entonces
     *       si el nodo previous  es null es porque al que se quiere eliminar es a la cabeza  entonces nunca entraron al bulce
     *           entonces la cabeza sera el valor que le seguia al mensajero o sea el valor sigueite a la cabeza
     *
     *
     *    */

    public void deleteByIdentification(String identification) {
        Node temp = head;
        Node previousNode = null;


        while (temp != null && !temp.getData().getIdentification().equals(identification)) {
            previousNode = temp;
            temp = temp.getNext();

        }
        if (temp != null) {
            if (previousNode == null) {
                head = temp.getNext();
            } else {
                previousNode.setNext(temp.getNext());
            }
        }


    }

    public void kidByGender() {
        ListSE tempBoys = new ListSE();
        ListSE tempGirls = new ListSE();
        Node temp = head;

        while (temp != null) {

            if (temp.getData().getGender()=='M') {
                tempBoys.add(temp.getData());
            } else {
                tempGirls.add(temp.getData());
            }
            temp = temp.getNext();
        }
        head = tempBoys.getHead();
        temp = head;
        while (temp.getNext() != null) {
            temp = temp.getNext();
        }
        temp.setNext(tempGirls.head);

    }

    public int rangeByAge(int min, int max) {
        Node temp = head;
        int counter = 0;
        while (temp !=  null) {
            if (temp.getData().getAge() >= min && temp.getData().getAge() <= max) {
                counter++;
            }
            temp= temp.getNext();
        }

        return counter;
    }



    public boolean sameKids(Kid kid){

        if( this.head!=null){
            Node temp = this.head;
            while(temp != null){
                if(temp.getData().getName().equals(kid.getName()) && temp.getData().getLocation().equals(kid.getLocation())){
                    return false;
                }
                temp = temp.getNext();
            }
        }
        return true;
    }

    public int getCountKidsByLocationCode(String code){
        int count =0;
        if( this.head!=null){
            Node temp = this.head;
            while(temp != null){
                if(temp.getData().getLocation().getCode().equals(code)){
                    count++;
                }
                temp = temp.getNext();
            }
        }
        return count;
    }





    public int getCountKidsByLocationCodeLimited(String code){
        int count =0;
        if( this.head!=null){
            Node temp = this.head;
            while(temp != null){
                if(temp.getData().getLocation().getCode().substring(0, 5).equals(code)){
                    count++;
                }
                temp = temp.getNext();
            }
        }
        return count;
    }

    public int getCountKidsByCity(String code){
        int count =0;
        if (code.length()==8){
             count = getCountKidsByLocationCode(code);
        }
        return count;

    }


    public int kidsByCityByGenderAndAge(String code, char gender, int age) {

        int count = 0;
        if (this.head != null) {
            Node temp = this.head;
            while (temp != null) {
                if (code.length() == 8) {
                    if (temp.getData().getLocation().getCode().equals(code) &&
                            temp.getData().getGender() == gender && temp.getData().getAge() > age) {
                        count++;

                    }
                }

                temp = temp.getNext();
            }
        }
        return count;
}
    public void invert (){
        if(this.head != null) {
            ListSE tempList = new ListSE();
            Node temp = this.head;
            while (temp != null) {
                tempList.addToStart(temp.getData());
                temp = temp.getNext();
            }
            this.head = tempList.getHead();
        }
    }
    public void deleteKidsByAge(byte age) {
        if (head != null) {
            ListSE tempList = new ListSE();
            Node temp = head;

            while (temp != null) {
                if (temp.getData().getAge() != age) {
                    tempList.add(temp.getData());
                }

                temp = temp.getNext();

            }
            head = tempList.getHead();
        }

    }
    public void intercalateByGender(){
        ListSE tempBoys = new ListSE();
        ListSE tempGirls = new ListSE();
        Node temp = head;
        while (temp != null){
            if(temp.getData().getGender()=='M'){
                tempBoys.add(temp.getData());
            }
            if(temp.getData().getGender()=='F'){
                tempGirls.add(temp.getData());
            }
            temp = temp.getNext();
        }
       
        ListSE intercalateList = new ListSE();
        Node maleNode = tempBoys.getHead();
        Node femaleNode = tempGirls.getHead();
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
    public float averageAge(){
        if (head != null){
            Node temp = head;
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



    public void sendBottomByLetter(char letter){
        if(this.head !=null){
            ListSE tempList = new ListSE();
            Node temp = this.head;
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
    public void AdvancePosition (String id, int motion,ListSE listSE) {
        if (head != null) {
            Node temp = this.head;

            int count = 1;

            while(temp!=null && !temp.getData().getIdentification().equals(id)){

                    temp = temp.getNext();
                    count++;

            }
            int diferencia = motion-count;
            Kid kidc = temp.getData();
            listSE.deleteByIdentification(temp.getData().getIdentification());
            listSE.addInPosition(diferencia,kidc);
        }
    }

    public void LostPosition (String id, int motion,ListSE listSE) {
        if (head != null) {
            Node temp = this.head;

            int count = 1;

            while(temp!=null && !temp.getData().getIdentification().equals(id)){

                temp = temp.getNext();
                count++;

            }
            int diferencia = motion+count-1;
            if (temp!=null){
            Kid kidc = temp.getData();
            listSE.deleteByIdentification(temp.getData().getIdentification());
            listSE.addInPosition(diferencia,kidc);
            }
        }
    }


}






