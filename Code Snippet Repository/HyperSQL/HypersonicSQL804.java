    public String[] getSchemaNamesArray() {

        readLock.lock();

        try {
            String[] array = new String[schemaMap.size()];

            schemaMap.toKeysArray(array);

            return array;
        } finally {
            readLock.unlock();
        }
    }
