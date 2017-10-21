    private synchronized void releaseServerSocket() {

        printWithThread("releaseServerSocket() entered");

        if (socket != null) {
            printWithThread("Releasing server socket: [" + socket + "]");
            setState(ServerConstants.SERVER_STATE_CLOSING);

            try {
                socket.close();
            } catch (IOException e) {
                printError("Exception closing server socket");
                printError("releaseServerSocket(): " + e);
            }

            socket = null;
        }

        printWithThread("releaseServerSocket() exited");
    }
