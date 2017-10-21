    public void testDoubleIntLookup() throws Exception {

        boolean failed   = false;
        int     testSize = 512;
        org.hsqldb.lib.IntKeyHashMap hIntMap =
            new org.hsqldb.lib.IntKeyHashMap();
        DoubleIntIndex intLookup = new DoubleIntIndex(12, false);

        try {
            intLookup.setKeysSearchTarget();
            populateBySerialIntKeysInt(intLookup, hIntMap, testSize);
            compareByHIteratorInt(intLookup, hIntMap);
            populateByRandomIntKeysInt(intLookup, hIntMap, testSize);
            compareByHIteratorInt(intLookup, hIntMap);
        } catch (Exception e) {
            failed = true;
        }

        assertTrue(!failed);
    }
