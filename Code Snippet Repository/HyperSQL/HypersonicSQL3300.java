    void compareByHIteratorInt(java.util.HashMap uMap,
                               org.hsqldb.lib.IntKeyHashMap hMap)
                               throws Exception {

        org.hsqldb.lib.Iterator hIt = hMap.keySet().iterator();

        for (int i = 0; hIt.hasNext(); i++) {
            Object hKey = new Integer(hIt.nextInt());
            Object oU   = uMap.get(hKey);
            Object hU   = hMap.get(((Integer) hKey).intValue());

            if (!oU.equals(hU)) {
                throw new Exception("HashMap value mismatch");
            }
        }
    }
