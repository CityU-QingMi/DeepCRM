    void populateBySerialIntKeysInt(java.util.HashMap uMap,
                                    org.hsqldb.lib.IntKeyHashMap hMap,
                                    int testSize) throws Exception {

        for (int i = 0; i < testSize; i++) {
            int intValue = randomgen.nextInt();

            uMap.put(new Integer(i), new Integer(intValue));
            hMap.put(i, new Integer(intValue));

            if (uMap.size() != hMap.size()) {
                throw new Exception("HashMap size mismatch");
            }
        }
    }
