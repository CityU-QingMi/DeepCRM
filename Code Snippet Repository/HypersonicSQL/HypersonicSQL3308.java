    void populateBySerialIntKeys(java.util.HashMap uMap,
                                 org.hsqldb.lib.HashMap hMap,
                                 int testSize) throws Exception {

        for (int i = 0; i < testSize; i++) {
            int intValue = randomgen.nextInt();

            uMap.put(new Integer(i), new Integer(intValue));
            hMap.put(new Integer(i), new Integer(intValue));

            if (uMap.size() != hMap.size()) {
                throw new Exception("HashMap size mismatch");
            }
        }
    }
