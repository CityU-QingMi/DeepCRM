    public int getLookup(int hash) {

        if (elementCount == 0) {
            return -1;
        }

        int index = (hash & 0x7fffffff) % hashTable.length;

        return hashTable[index];
    }
