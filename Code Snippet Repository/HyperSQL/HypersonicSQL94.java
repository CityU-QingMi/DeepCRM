    public static void shutdownDatabases(Notified server, int shutdownMode) {

        Database[] dbArray;

        synchronized (serverMap) {
            HashSet databases = (HashSet) serverMap.get(server);

            if (databases == null) {
                dbArray = new Database[0];
            } else {
                dbArray = new Database[databases.size()];

                databases.toArray(dbArray);
            }
        }

        for (int i = 0; i < dbArray.length; i++) {
            dbArray[i].close(shutdownMode);
        }
    }
