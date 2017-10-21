    private int getBlockAddress(int[][] blockAddresses, int blockOffset) {

        for (int i = 0; i < blockAddresses.length; i++) {
            if (blockAddresses[i][LOBS.BLOCK_OFFSET]
                    + blockAddresses[i][LOBS.BLOCK_COUNT] > blockOffset) {
                return blockAddresses[i][LOBS.BLOCK_ADDR]
                       - blockAddresses[i][LOBS.BLOCK_OFFSET] + blockOffset;
            }
        }

        return -1;
    }
