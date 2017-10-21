    public void testIntKeyHashMap() throws Exception {

        boolean failed   = false;
        int     testSize = 33;
        org.hsqldb.lib.IntKeyHashMap hIntMap =
            new org.hsqldb.lib.IntKeyHashMap();
        java.util.HashMap uMap = new java.util.HashMap();

        try {
            populateBySerialIntKeysInt(uMap, hIntMap, testSize);
            compareByUIteratorInt(uMap, hIntMap);
            populateByRandomIntKeysInt(uMap, hIntMap, testSize);
            compareByUIteratorInt(uMap, hIntMap);
            compareByHIteratorInt(uMap, hIntMap);

            //
            depopulateByIntIterator(uMap, hIntMap, 20);
            compareByUIteratorInt(uMap, hIntMap);
            compareByHIteratorInt(uMap, hIntMap);

            //
            clearByIntIterator(uMap, hIntMap);
            compareByUIteratorInt(uMap, hIntMap);
            compareByHIteratorInt(uMap, hIntMap);

            // -
            populateBySerialIntKeysInt(uMap, hIntMap, testSize);
            compareByUIteratorInt(uMap, hIntMap);
            compareByHIteratorInt(uMap, hIntMap);

            //
            clearByIntIterator(uMap, hIntMap);
            compareByUIteratorInt(uMap, hIntMap);
            compareByHIteratorInt(uMap, hIntMap);
        } catch (Exception e) {
            failed = true;
        }

        assertTrue(!failed);
    }
