    public void checkRunning(boolean running) {

        int     state;
        boolean error;

        printWithThread("checkRunning(" + running + ") entered");

        state = getState();
        error = (running && state != ServerConstants.SERVER_STATE_ONLINE)
                || (!running
                    && state != ServerConstants.SERVER_STATE_SHUTDOWN);

        if (error) {
            String msg = "server is " + (running ? "not "
                                                 : "") + "running";

            throw Error.error(ErrorCode.GENERAL_ERROR, msg);
        }

        printWithThread("checkRunning(" + running + ") exited");
    }
