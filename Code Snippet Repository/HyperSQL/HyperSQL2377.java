    public synchronized void signalCloseAllServerConnections() {

        Iterator           it;
        ServerConnection[] array;

        printWithThread("signalCloseAllServerConnections() entered");

        synchronized (serverConnSet) {

            // snapshot
            array = new ServerConnection[serverConnSet.size()];

            serverConnSet.toArray(array);
        }

        for (int i = 0; i < array.length; i++) {
            ServerConnection sc = array[i];

            printWithThread("Closing " + sc);

            // also removes all but one connection from serverConnSet
            sc.signalClose();
        }

        printWithThread("signalCloseAllServerConnections() exited");
    }
