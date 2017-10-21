    public void stop() {

        System.err.println("Stopping");

        Thread t = buttonUpdaterThread;

        if (t != null) {
            t.setContextClassLoader(null);
        }

        buttonUpdaterThread = null;
    }
