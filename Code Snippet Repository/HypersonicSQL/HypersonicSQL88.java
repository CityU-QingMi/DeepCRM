    private static void registerServer(Notified server, Database db) {

        synchronized (serverMap) {
            if (!serverMap.containsKey(server)) {
                serverMap.put(server, new HashSet());
            }

            HashSet databases = (HashSet) serverMap.get(server);

            databases.add(db);
        }
    }
