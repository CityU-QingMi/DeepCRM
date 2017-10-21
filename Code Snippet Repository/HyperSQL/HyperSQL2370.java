    public int start() {

        printWithThread("start() entered");

        int previousState = getState();

        if (serverThread != null) {
            printWithThread("start(): serverThread != null; no action taken");

            return previousState;
        }

        setState(ServerConstants.SERVER_STATE_OPENING);

        serverThread = new ServerThread("HSQLDB Server ");

        if (isDaemon) {
            serverThread.setDaemon(true);
        }

        serverThread.start();

        // call synchronized getState() to become owner of the Server Object's monitor
        while (getState() == ServerConstants.SERVER_STATE_OPENING) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }

        printWithThread("start() exiting");

        return previousState;
    }
