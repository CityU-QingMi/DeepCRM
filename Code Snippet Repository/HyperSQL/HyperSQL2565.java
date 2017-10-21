    public void testDoubleIntSpeed() throws Exception {

        boolean failed   = false;
        int     testSize = 500;
        org.hsqldb.lib.IntKeyHashMap hIntMap =
            new org.hsqldb.lib.IntKeyHashMap();
        DoubleIntIndex intLookup = new DoubleIntIndex(testSize, false);

        intLookup.setKeysSearchTarget();
        populateByRandomIntKeysInt(intLookup, hIntMap, testSize);
        compareByHIteratorInt(intLookup, hIntMap);

        int[]     sample     = getSampleIntArray(intLookup, 100);
        int[]     sampleVals = new int[sample.length];
        int       i          = 0;
        int       j          = 0;
        StopWatch sw         = new StopWatch();

        try {
            for (j = 0; j < 10000; j++) {
                for (i = 0; i < sample.length; i++) {
                    int pos = intLookup.findFirstEqualKeyIndex(sample[i]);

                    sampleVals[i] = intLookup.getValue(pos);

                    intLookup.remove(pos);
                }

                for (i = 0; i < sample.length; i++) {
                    intLookup.addUnique(sample[i], sampleVals[i]);
                }
            }

            System.out.println(
                sw.elapsedTimeToMessage("Double int table times"));
            intLookup.findFirstEqualKeyIndex(0);    // sort
            compareByHIteratorInt(intLookup, hIntMap);
        } catch (Exception e) {
            System.out.println(
                sw.elapsedTimeToMessage("Double int table error: i =" + i));

            failed = true;
        }

        assertTrue(!failed);
    }
