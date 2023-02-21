package LAB2;

import java.util.Scanner;

class Card {
    private int id;
    private int power;
    private int numAttacks;

    public Card(int id, int power, int numAttacks) {
        this.id = id;
        this.power = power;
        this.numAttacks = numAttacks;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getNumAttacks() {
        return numAttacks;
    }

    public void setNumAttacks(int numAttacks) {
        this.numAttacks = numAttacks;
    }

    public int damage() {
        return power * numAttacks;
    }


    @Override
    public String toString() {
        return String.valueOf(id);
    }
}



public class Heroes {

    //todo: implement function
    public static void startHeroesGame(SLL<Card> firstFriendCards, SLL<Card> secondFriendCards)
    {



            SLLNode<Card> tmp1=firstFriendCards.getFirst();
            SLLNode<Card> tmp2=secondFriendCards.getFirst();
            int naj=tmp1.element.getNumAttacks()*tmp1.element.getPower();
            int ID=0;
            for(int i=1;i<6;i++)
            {
                tmp1=tmp1.succ;
                int mokj=tmp1.element.getNumAttacks()*tmp1.element.getPower();
                //System.out.println(mokj);
                if(mokj>naj)
                {
                    naj=mokj;
                    tmp2=tmp1;
                    //ID=tmp1.element.getId();
                }
            }

            secondFriendCards.insertAfter(tmp2.element,secondFriendCards.getFirst().succ.succ);
            firstFriendCards.delete(tmp2);
        }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SLL<Card> firstFriendCards = new SLL<Card>();
        SLL<Card> secondFriendCards = new SLL<Card>();

        for (int i = 0; i < 6; i++) {
            String line = scanner.nextLine();
            String[] parts = line.split("\\s+");
            firstFriendCards.insertLast(new Card(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]), Integer.parseInt(parts[2])));
        }

        for (int i = 0; i < 6; i++) {
            String line = scanner.nextLine();
            String[] parts = line.split("\\s+");
            secondFriendCards.insertLast(new Card(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]), Integer.parseInt(parts[2])));
        }

        startHeroesGame(firstFriendCards, secondFriendCards);
        System.out.println(firstFriendCards.toString());
        System.out.println(secondFriendCards.toString());
    }
}