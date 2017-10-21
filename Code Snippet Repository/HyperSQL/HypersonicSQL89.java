    private static void notifyServers(Database db) {

        Notified[] servers;

        synchronized (serverMap) {
            servers = new Notified[serverMap.size()];

            serverMap.keysToArray(servers);
        }

        for (int i = 0; i < servers.length; i++) {
            Notified server = servers[i];
            HashSet  databases;
            boolean  removed = false;

            synchronized (serverMap) {
                databases = (HashSet) serverMap.get(server);
            }

            if (databases != null) {
                synchronized (databases) {
                    removed = databases.remove(db);
                }
            }

            if (removed) {
                server.notify(db.databaseID);
            }
        }
    }
