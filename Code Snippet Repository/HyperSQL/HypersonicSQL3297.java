    void compareByHIterator(java.util.HashMap uMap,
                            org.hsqldb.lib.HashMap hMap) throws Exception {

        org.hsqldb.lib.Iterator hIt = hMap.keySet().iterator();

        for (int i = 0; hIt.hasNext(); i++) {
            Object hKey = hIt.next();
            Object oU   = uMap.get(hKey);
            Object hU   = hMap.get(hKey);

            if (!oU.equals(hU)) {
                throw new Exception("HashMap value mismatch");
            }
        }
    }
