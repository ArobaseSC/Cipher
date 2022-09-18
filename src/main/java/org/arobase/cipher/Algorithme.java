package org.arobase.cipher;

import java.util.ArrayList;
import java.util.List;

public class Algorithme {

    private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static String cesarChiffrement(String texte, int key){
        StringBuilder result = new StringBuilder();
        for(int i = 0 ; i < texte.length() ; i++){
            char c = texte.charAt(i);
            if(Character.isLetter(c)){
                int index = ALPHABET.indexOf(Character.toUpperCase(c));
                int newIndex = (index + key) % ALPHABET.length();
                if(Character.isUpperCase(c)){
                    result.append(ALPHABET.charAt(newIndex));
                }else{
                    result.append(Character.toLowerCase(ALPHABET.charAt(newIndex)));
                }
            }else{
                result.append(c);
            }
        }
        return result.toString();
    }

    public static String cesarDechiffrement(String texte, int key){
        StringBuilder result = new StringBuilder();
        for(int i = 0 ; i < texte.length() ; i++){
            char c = texte.charAt(i);
            if(Character.isLetter(c)){
                int index = ALPHABET.indexOf(Character.toUpperCase(c));
                int newIndex = index - key >= 0 ? (index - key) % ALPHABET.length() : (index - key) % ALPHABET.length() + ALPHABET.length();
                if(Character.isUpperCase(c)){
                    result.append(ALPHABET.charAt(newIndex));
                }else{
                    result.append(Character.toLowerCase(ALPHABET.charAt(newIndex)));
                }
            }else{
                result.append(c);
            }
        }
        return result.toString();
    }

    public static String vigenereChiffrement(String texte, String key){
        StringBuilder result = new StringBuilder();

        List<Integer> keyIndexs = new ArrayList<>();
        for (int i = 0; i < key.length(); i++) {
            keyIndexs.add(ALPHABET.indexOf(Character.toUpperCase(key.charAt(i))));
        }

        int incrementation = 0;

        for(int i = 0 ; i < texte.length() ; i++){
            char c = texte.charAt(i);
            if(Character.isLetter(c)){
                int index = ALPHABET.indexOf(Character.toUpperCase(c));
                int newIndex = (index + keyIndexs.get(incrementation % keyIndexs.size())) % ALPHABET.length();
                incrementation++;
                if(Character.isUpperCase(c)){
                    result.append(ALPHABET.charAt(newIndex));
                }else{
                    result.append(Character.toLowerCase(ALPHABET.charAt(newIndex)));
                }
            }else{
                result.append(c);
            }
        }
        return result.toString();
    }

    public static String vigenereDechiffrement(String texte, String key){
        StringBuilder result = new StringBuilder();

        List<Integer> keyIndexs = new ArrayList<>();
        for (int i = 0; i < key.length(); i++) {
            keyIndexs.add(ALPHABET.indexOf(Character.toUpperCase(key.charAt(i))));
        }

        int incrementation = 0;

        for(int i = 0 ; i < texte.length() ; i++){
            char c = texte.charAt(i);
            if(Character.isLetter(c)){
                int index = ALPHABET.indexOf(Character.toUpperCase(c));
                int newIndex = (index + keyIndexs.get(incrementation % keyIndexs.size())) % ALPHABET.length();
                incrementation++;
                if(Character.isUpperCase(c)){
                    result.append(ALPHABET.charAt(newIndex));
                }else{
                    result.append(Character.toLowerCase(ALPHABET.charAt(newIndex)));
                }
            }else{
                result.append(c);
            }
        }
        return result.toString();
    }

    public static List<String> brutForce(String texte){

        List<String> propositions = new ArrayList<>();
        StringBuilder stringBuilder;

        for(int k = 1; k < ALPHABET.length(); k++){
            stringBuilder = new StringBuilder();
            for (Character c : texte.toCharArray()) {
                if(Character.isLetter(c)) {
                    char newChar = ALPHABET.charAt((ALPHABET.indexOf(Character.toUpperCase(c)) + k) % ALPHABET.length());
                    newChar = Character.isUpperCase(c) ? Character.toUpperCase(newChar) : Character.toLowerCase(newChar);
                    stringBuilder.append(newChar);
                }else{
                    stringBuilder.append(c);
                }
            }
            propositions.add("Clef : "+k+" Message : "+ stringBuilder);
            System.out.println("Clef : "+k+" Message : "+stringBuilder);
        }
        return propositions;
    }


}
