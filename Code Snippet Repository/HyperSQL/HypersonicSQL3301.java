    void compareByHIteratorInt(DoubleIntIndex intLookup,
                               org.hsqldb.lib.IntKeyHashMap hMap)
                               throws Exception {

        org.hsqldb.lib.Iterator hIt = hMap.keySet().iterator();

        for (int i = 0; hIt.hasNext(); i++) {
            int     hK     = hIt.nextInt();
            int     lookup = intLookup.findFirstEqualKeyIndex(hK);
            int     lV     = intLookup.getValue(lookup);
            Integer hV     = (Integer) hMap.get(hK);

            if (hV.intValue() != lV) {
                throw new Exception("HashMap value mismatch");
            }
        }
    }
