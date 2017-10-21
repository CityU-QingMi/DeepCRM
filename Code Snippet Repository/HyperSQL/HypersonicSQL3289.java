    void populateByRandomIntKeysInt(DoubleIntIndex intLookup,
                                    org.hsqldb.lib.IntKeyHashMap hMap,
                                    int testSize) throws Exception {

        for (int i = 0; i < testSize; i++) {
            int intValue = randomgen.nextInt();

            intLookup.addUnique(intValue, i);
            hMap.put(intValue, new Integer(i));

            // actually this can happen as duplicates are allowed in DoubleIntTable
            if (intLookup.size() != hMap.size()) {
                throw new Exception("Duplicate random in int lookup");
            }
        }
    }
