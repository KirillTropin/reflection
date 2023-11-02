package org.example;

import net.andreinc.markovneat.MChain;
import net.andreinc.markovneat.MState;

import java.util.HashMap;
import java.util.Map;

// generating by YandexGPT - можно спать спокойно, она не умеет дебажить, даже не понимает в чём ошибка
public class MarkovChain {
    private static int[][] transitionMatrix = {{0, 1, 0}, {0, 0, 2}, {1, 2, 3}};
    private int[] stateVector = new int[]{0, 1};

    public static void main(String[] args) {
        Map<Integer, Integer> stateVector = MarkovChain.getStateVector();
        double[] probabilities = MarkovChain.calculateProbabilities(stateVector);
        System.out.println("Probability of starting in state 0 and ending in state 1: " + probabilities[0] + " ");
        System.out.println("Probability of starting in state 1 and ending in state 2: " + probabilities[1] + " ");
    }

    private static Map<Integer, Integer> getStateVector() {
        Map<Integer, Integer> stateVectorMap = new HashMap<>();
        stateVectorMap.put(0, 0);
        stateVectorMap.put(1, 1);
        return stateVectorMap;
    }

    private static double[] calculateProbabilities(Map<Integer, Integer> stateVector) {
        double probability = 0;
        for (int i = 1; i < stateVector.size(); i++) {
            for (int j = 1; j < stateVector.get(i); j++) {
                probability += transitionMatrix[i][j] / transitionMatrix[j][j];
            }
        }
        return new double[]{probability};
    }

    private static void mansCodeChain() {
        MChain<String> marketMChain = new MChain<>();
// Transitioning from "BULL" to "BULL" has a 90% chance
        marketMChain.add(new MState<>("BULL"), "BULL", 0.9);
// Transitioning from "BULL" to "BEAR" has a 7,5% chance
        marketMChain.add(new MState<>("BULL"), "BEAR", 0.075);
// Transitioning from "BULL" to "STAGNANT" has a 2,5% chance
        marketMChain.add(new MState<>("BULL"), "STAGNANT", 0.025);

        marketMChain.add(new MState<>("BEAR"), "BEAR", 0.8);
        marketMChain.add(new MState<>("BEAR"), "BULL", 0.15);
        marketMChain.add(new MState<>("BEAR"), "STAGNANT", 0.05);

        marketMChain.add(new MState<>("STAGNANT"), "STAGNANT", 0.5);
        marketMChain.add(new MState<>("STAGNANT"), "BULL", 0.25);
        marketMChain.add(new MState<>("STAGNANT"), "BEAR", 0.25);

        marketMChain.generate(10000).forEach(System.out::println);
    }
}
