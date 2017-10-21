    void depopulateRandomly(java.util.HashMap uMap,
                            org.hsqldb.lib.HashMap hMap,
                            int testCount) throws Exception {

        int removeCount = 0;
        int size        = uMap.size();

        if (testCount > size / 2) {
            testCount = size / 2;
        }

        while (removeCount < testCount) {
            java.util.Iterator uIt = uMap.keySet().iterator();

            for (int i = 0; uIt.hasNext(); i++) {
                Object uKey     = uIt.next();
                int    intValue = randomgen.nextInt(size);

                if (intValue == i) {
                    uIt.remove();
                    hMap.remove(uKey);

                    removeCount++;
                }

                if (uMap.size() != hMap.size()) {
                    throw new Exception("HashMap size mismatch");
                }
            }
        }
    }
