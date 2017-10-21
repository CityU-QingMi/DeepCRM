    public String[] getTablePropsSQL(boolean withHeader) {

        readLock.lock();

        try {
            HsqlArrayList tableList = getAllTables(false);
            HsqlArrayList list      = new HsqlArrayList();

            for (int i = 0; i < tableList.size(); i++) {
                Table t = (Table) tableList.get(i);

                if (t.isText()) {
                    String[] ddl = t.getSQLForTextSource(withHeader);

                    list.addAll(ddl);
                }

                String ddl = t.getSQLForReadOnly();

                if (ddl != null) {
                    list.add(ddl);
                }

                if (t.isCached()) {
                    ddl = t.getSQLForClustered();

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
