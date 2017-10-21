    void signalClose() {

        keepAlive = false;

        if (Thread.currentThread().equals(runnerThread)) {
            Result resultOut = Result.updateZeroResult;

            try {
                resultOut.write(session, dataOutput, rowOut);
            } catch (Throwable t) {}
        }

        close();
    }
