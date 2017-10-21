    public String[] getIndexRootsSQL() {

        readLock.lock();

        try {
            Session       sysSession = database.sessionManager.getSysSession();
            long[][]      rootsArray = getIndexRoots(sysSession);
            HsqlArrayList tableList  = getAllTables(true);
            HsqlArrayList list       = new HsqlArrayList();

            for (int i = 0; i < rootsArray.length; i++) {
                Table table = (Table) tableList.get(i);

                if (rootsArray[i] != null && rootsArray[i].length > 0
                        && rootsArray[i][0] != -1) {
                    String ddl = table.getIndexRootsSQL(rootsArray[i]);

                    list.add(ddl);
                }
            }

            String[] array = new String[list.size()];

            list.toArray(array);

            return array;
        } finally {
            readLock.unlock();
        }
    }
