package org.basicchain.model;

import org.basicchain.util.StringUtil;

import java.time.LocalDateTime;


public class Block {
    public String hash;
    public String previousHash;
    private String data;
    private LocalDateTime timeStamp;
    private int nonce;

    public Block(String data, String previousHash) {
        this.previousHash = previousHash;
        this.data = data;
        this.timeStamp = LocalDateTime.now();
        this.hash = calculateHash();
    }

    public String calculateHash() {
        // This will calculate the block hash
        return StringUtil.applySha256(previousHash + timeStamp.toString() + data);
    }

    public void mineBlock(final int difficulty) {
        //Create a string with difficulty * "0"
        String target = new String(new char[difficulty]).replace('\0', '0');
        while (!hash.substring(0, difficulty).equals(target)) {
            nonce++;
            hash = calculateHash();
        }
        System.out.println("Block Mined!!! : " + hash);
    }

}