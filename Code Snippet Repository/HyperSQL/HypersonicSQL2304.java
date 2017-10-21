    public void reset(int hashTableSize, int capacity) {

        int[] newHT = new int[hashTableSize];
        int[] newLT = new int[capacity];

        // allocate memory before assigning
        hashTable = newHT;
        linkTable = newLT;

        Arrays.fill(hashTable, -1);
        resetTables();
    }
