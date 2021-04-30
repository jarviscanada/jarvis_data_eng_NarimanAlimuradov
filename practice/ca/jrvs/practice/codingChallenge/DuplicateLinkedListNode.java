package ca.jrvs.practice.codingChallenge;

import ca.jrvs.practice.dataStructures.list.LinkedJList;

import java.util.HashSet;

public class DuplicateLinkedListNode {

    public static void main(String[] args) {

        LinkedJList<Integer> ll = new LinkedJList<>();

        ll.add(1);
        ll.add(2);
        ll.add(3);
        ll.add(2);
        ll.add(4);
    }

    public static LinkedJList removeDuplicates(LinkedJList linkedList){
        HashSet set = new HashSet();
        for (int i = 0; i < linkedList.size(); i++){

        }
        return null;
    }

}
