    public HsqlName[] getCatalogAndBaseTableNames() {

        readLock.lock();

        try {
            OrderedHashSet names  = new OrderedHashSet();
            HsqlArrayList  tables = getAllTables(false);

            for (int i = 0; i < tables.size(); i++) {
                Table table = (Table) tables.get(i);

                if (!table.isTemp()) {
                    names.add(table.getName());
                }
            }

            names.add(database.getCatalogName());

            HsqlName[] array = new HsqlName[names.size()];

            names.toArray(array);

            return array;
        } finally {
            readLock.unlock();
        }
    }
