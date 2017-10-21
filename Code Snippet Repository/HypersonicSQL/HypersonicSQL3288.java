    public void testHashMap() throws Exception {

        boolean                failed   = false;
        int                    testSize = 33;
        org.hsqldb.lib.HashMap hMap     = new org.hsqldb.lib.HashMap();
        org.hsqldb.lib.IntKeyHashMap hIntMap =
            new org.hsqldb.lib.IntKeyHashMap();
        java.util.HashMap uMap = new java.util.HashMap();

        try {
            populateBySerialIntKeys(uMap, hMap, testSize);
            compareByUIterator(uMap, hMap);
            compareByHIterator(uMap, hMap);

            // -
            populateByRandomIntKeys(uMap, hMap, testSize);
            compareByUIterator(uMap, hMap);
            compareByHIterator(uMap, hMap);

            //
            depopulateRandomly(uMap, hMap, 20);
            compareByUIterator(uMap, hMap);
            compareByHIterator(uMap, hMap);

            // -
            populateBySerialIntKeys(uMap, hMap, testSize);
            compareByUIterator(uMap, hMap);
            compareByHIterator(uMap, hMap);

            //
            depopulateByIterator(uMap, hMap, 20);
            compareByUIterator(uMap, hMap);
            compareByHIterator(uMap, hMap);
        } catch (Exception e) {
            failed = true;
        }

        assertTrue(!failed);
    }
