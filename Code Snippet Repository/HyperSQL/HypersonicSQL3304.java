    public void testHashMappedList() throws Exception {

        boolean failed   = false;
        int     testSize = 33;
        org.hsqldb.lib.HashMappedList hMap =
            new org.hsqldb.lib.HashMappedList();
        java.util.HashMap uMap = new java.util.HashMap();

        try {
            populateBySerialIntKeys(uMap, hMap, testSize);
            compareByUIterator(uMap, hMap);
            compareByHIterator(uMap, hMap);
            populateByRandomIntKeys(uMap, hMap, testSize);
            compareByUIterator(uMap, hMap);
            compareByHIterator(uMap, hMap);
            depopulateRandomly(uMap, hMap, 20);
            compareByUIterator(uMap, hMap);
            compareByHIterator(uMap, hMap);
            populateByRandomIntKeys(uMap, hMap, testSize);
            compareByUIterator(uMap, hMap);
            compareByHIterator(uMap, hMap);
            depopulateRandomly(uMap, hMap, 20);
            populateBySerialIntKeys(uMap, hMap, testSize);
            compareByUIterator(uMap, hMap);
            compareByHIterator(uMap, hMap);
        } catch (Exception e) {
            failed = true;
        }

        assertTrue(!failed);
    }
