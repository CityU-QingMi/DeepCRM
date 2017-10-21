    public int stop() {

        printWithThread("stop() entered");

        int previousState = getState();

        if (serverThread == null) {
            printWithThread("stop() serverThread is null; no action taken");

            return previousState;
        }

        releaseServerSocket();
        printWithThread("stop() exiting");

        return previousState;
    }
