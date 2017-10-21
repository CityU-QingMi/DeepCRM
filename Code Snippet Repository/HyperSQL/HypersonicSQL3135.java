    final synchronized void releaseDatabase(int id) {

        ServerConnection[] array;

        printWithThread("releaseDatabase(" + id + ") entered");

        // check all slots as a database may be opened by multiple aliases
        for (int i = 0; i < dbID.length; i++) {
            if (dbID[i] == id && dbAlias[i] != null) {
                dbID[i]             = 0;
                dbActionSequence[i] = 0;
                dbAlias[i]          = null;
                dbPath[i]           = null;
                dbType[i]           = null;
                dbProps[i]          = null;
            }
        }

        synchronized (serverConnSet) {
            array = new ServerConnection[serverConnSet.size()];

            serverConnSet.toArray(array);
        }

        for (int i = 0; i < array.length; i++) {
            ServerConnection sc = array[i];

            if (sc.dbID == id) {
                sc.signalClose();
            }
        }

        printWithThread("releaseDatabase(" + id + ") exiting");
    }
