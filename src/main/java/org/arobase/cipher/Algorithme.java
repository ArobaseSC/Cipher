package org.arobase.cipher;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class Algorithme {

    /**
     * Constante to represent the alphabet
     */
    private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    /**
     * Chiffrage avec le code César à l'aide d'une clé entière
     * @param texte Texte a chiffrer
     * @param key Clé représenter par un nombre
     * @return Message chiffré
     */
    public static String cesarChiffrement(String texte, int key){
        //Création d'un stringBuilder
        StringBuilder result = new StringBuilder();
        //Parcours des lettres constituant le mot
        for(Character c : texte.toCharArray()){
            //Si le charactère est une lettre, on le traite
            if(Character.isLetter(c)){
                //On récupere la nouvelle lettre par rapport à l'indice de la lettre original + la clé.
                //On fait un modulo par rapport à la taille de l'alphabet pour rester dans l'intervalle.
                char newChar = ALPHABET.charAt((ALPHABET.indexOf(Character.toUpperCase(c)) + key) % ALPHABET.length());

                //Si la lettre était en minuscule
                if(Character.isLowerCase(c)) {
                    //On la retransforme en minuscule
                    newChar = Character.toLowerCase(newChar);
                }

                //On ajoute notre nouvelle lettre au resultat
                result.append(newChar);

            //Sinon on l'ajoute tel qu'elle est
            }else{
                result.append(c);
            }
        }
        return result.toString();
    }

    /**
     * Déchiffrage avec le code César à l'aide d'une clé entière
     * @param texte Texte a déchiffrer
     * @param key Clé représenter par un nombre
     * @return Message déchiffré
     */
    public static String cesarDechiffrement(String texte, int key){
        //Création d'un stringBuilder
        StringBuilder result = new StringBuilder();
        //Parcours des lettres constituant le mot
        for(Character c : texte.toCharArray()){
            //Si le charactère est une lettre, on la traite
            if(Character.isLetter(c)){
                //On récupere la nouvelle lettre par rapport à l'indice de la lettre original - la clé.
                //On fait un modulo par rapport à la taille de l'alphabet pour rester dans l'intervalle.
                char newChar = ALPHABET.charAt((ALPHABET.indexOf(Character.toUpperCase(c)) - key + ALPHABET.length()) % ALPHABET.length());
                //Si la lettre était en minuscule
                if(Character.isLowerCase(c)) {
                    //On la retransforme en minuscule
                    newChar = Character.toLowerCase(newChar);
                }
                //On ajoute notre nouvelle lettre au resultat
                result.append(newChar);
            //Sinon on l'ajoute tel qu'elle est
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


    public static String cesarBlockDechiffrementParChiffre(String texte, int key){
        String[] tab  = texte.split("\\.");
        StringBuilder result = new StringBuilder();
        StringBuilder stringBuilder;
        DecimalFormat nf2 = new DecimalFormat("0000");
        for (String s : tab) {
            int index = Integer.parseInt(s);
            if (index-key<0){
                index = 2526+(index-key);
            }else{
                index = index-key;
            }
            stringBuilder = new StringBuilder(nf2.format(index));
            for (int j = 0; j < stringBuilder.length(); j+=2) {
                String temp2 = ""+stringBuilder.charAt(j)+stringBuilder.charAt(j+1);
                index = Integer.parseInt(temp2);
                if (index>25){
                    index = index%26;
                }
                result.append(ALPHABET.charAt(index));
            }
        }
        return result.toString();
    }

    public static String cesarBlockChiffrementParChiffre(String texte, int key){
        String[] tab  = texte.split("\\.");
        StringBuilder result = new StringBuilder();
        StringBuilder stringBuilder;
        DecimalFormat nf2 = new DecimalFormat("0000");
        for (String s : tab) {
            int index = Integer.parseInt(s);
            if (index+key>2526){
                index = (index+key)-2526;
            }else{
                index = index+key;
            }
            stringBuilder = new StringBuilder(nf2.format(index));
            for (int j = 0; j < stringBuilder.length(); j+=2) {
                String temp2 = ""+stringBuilder.charAt(j)+stringBuilder.charAt(j+1);
                index = Integer.parseInt(temp2);
                if (index>25){
                    index = index%26;
                }
                result.append(ALPHABET.charAt(index));
            }
        }
        return result.toString();
    }



    public static String cesarAffineChiffrement(String message, int a, int b){
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < message.length(); i++) {
            char c = Character.toUpperCase(message.charAt(i));
            if (ALPHABET.contains(String.valueOf(c))){
                int index = (ALPHABET.indexOf(c)*a+b)%26;
                result.append(ALPHABET.charAt(index));
            }else {
                result.append(c);
            }
        }
        return result.toString();
    }




}
