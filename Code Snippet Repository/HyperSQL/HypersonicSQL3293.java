    void depopulateByIterator(java.util.HashMap uMap,
                              org.hsqldb.lib.HashMap hMap,
                              int testCount) throws Exception {

        org.hsqldb.lib.Iterator hIt = hMap.keySet().iterator();

        System.out.println(uMap.size());

        for (int i = 0; hIt.hasNext(); i++) {
            Object key   = hIt.next();
            int    check = randomgen.nextInt(2);

            if (check == i % 2) {
                hIt.remove();
                uMap.remove(key);
            }

            if (uMap.size() != hMap.size()) {
                throw new Exception("HashMap size mismatch");
            }
        }

        System.out.println(uMap.size());
    }
