    void clearByIntIterator(java.util.HashMap uMap,
                            org.hsqldb.lib.IntKeyHashMap hIntMap)
                            throws Exception {

        org.hsqldb.lib.Iterator hIt = hIntMap.keySet().iterator();

        System.out.println(uMap.size());

        for (int i = 0; hIt.hasNext(); i++) {
            Object key = new Integer(hIt.nextInt());

            hIt.remove();
            uMap.remove(key);

            if (uMap.size() != hIntMap.size()) {
                throw new Exception("HashMap size mismatch");
            }
        }

        System.out.println(uMap.size());
    }
