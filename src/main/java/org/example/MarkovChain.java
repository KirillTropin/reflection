package org.example;

import java.util.HashMap;
import java.util.Map;

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
        for (int i = 0; i < stateVector.size(); i++) {
            for (int j = 0; j < stateVector.get(i); j++) {
                probability += transitionMatrix[i][j] / transitionMatrix[j][j];
            }
        }
        return new double[]{probability};
    }
}
