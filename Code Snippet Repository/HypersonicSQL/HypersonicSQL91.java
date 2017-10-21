    public static void closeDatabases(int mode) {

        synchronized (databaseIDMap) {
            Iterator it = databaseIDMap.values().iterator();

            while (it.hasNext()) {
                Database db = (Database) it.next();

                try {
                    db.close(mode);
                } catch (HsqlException e) {}
            }
        }
    }
