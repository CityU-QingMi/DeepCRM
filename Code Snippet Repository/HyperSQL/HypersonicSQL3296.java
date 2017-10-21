    void compareByUIterator(java.util.HashMap uMap,
                            org.hsqldb.lib.HashMap hMap) throws Exception {

        java.util.Iterator uIt = uMap.keySet().iterator();

        for (int i = 0; uIt.hasNext(); i++) {
            Object uKey = uIt.next();
            Object oU   = uMap.get(uKey);
            Object hU   = hMap.get(uKey);

            if (!oU.equals(hU)) {
                throw new Exception("HashMap value mismatch");
            }
        }
    }
