    void populateByRandomIntKeys(java.util.HashMap uMap,
                                 org.hsqldb.lib.HashMap hMap,
                                 int testSize) throws Exception {

        for (int i = 0; i < testSize; i++) {
            int intValue = randomgen.nextInt();

            uMap.put(new Integer(intValue), new Integer(i));
            hMap.put(new Integer(intValue), new Integer(i));

            if (uMap.size() != hMap.size()) {
                throw new Exception("HashMap size mismatch");
            }
        }
    }
