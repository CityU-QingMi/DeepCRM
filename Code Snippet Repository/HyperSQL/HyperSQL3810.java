    public String[] getTableSpaceSQL() {

        readLock.lock();

        try {
            HsqlArrayList tableList = getAllTables(false);
            HsqlArrayList list      = new HsqlArrayList();

            for (int i = 0; i < tableList.size(); i++) {
                Table t = (Table) tableList.get(i);

                if (t.isCached()) {
                    String ddl = t.getSQLForTableSpace();

                    if (ddl != null) {
                        list.add(ddl);
                    }
                }
            }

            String[] array = new String[list.size()];

            list.toArray(array);

            return array;
        } finally {
            readLock.unlock();
        }
    }
