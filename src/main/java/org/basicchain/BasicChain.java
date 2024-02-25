package org.basicchain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.basicchain.model.Block;

import java.util.ArrayList;
import java.util.List;

import static org.basicchain.validator.ChainValidator.isChainValid;

public class BasicChain {
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final List<Block> blockChain = new ArrayList<>();
    // Change this difficulty: 0 - INSTANT | 1 - 3 EASY | 3+ You can have a problem to mine
    public static final int DIFFICULTY = 0;


    public static void main(String[] args) {
        // Creating blocks
        blockChain.add(new Block("Hi I'm the Initial Block.", "0"));
        System.out.println("Trying to mine block 1... ");
        blockChain.get(0).mineBlock(DIFFICULTY);

        blockChain.add(new Block("Hi I'm the Second Block.", blockChain.get(blockChain.size() - 1).hash));
        System.out.println("Trying to mine block 2... ");
        blockChain.get(1).mineBlock(DIFFICULTY);

        blockChain.add(new Block("Hi I'm the Third Block.", blockChain.get(blockChain.size() - 1).hash));
        System.out.println("Trying to mine block 3... ");
        blockChain.get(2).mineBlock(DIFFICULTY);

        System.out.println("\nBlockchain is Valid: " + isChainValid(blockChain, DIFFICULTY));

        try {
            String blockChainJson = objectMapper.writeValueAsString(blockChain);
            System.out.println("The Block Chain: " + blockChainJson);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }
}