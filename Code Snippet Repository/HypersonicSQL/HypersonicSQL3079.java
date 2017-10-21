    public void run() {

        try {
            if (writeDelay != 0) {
                sync();
            }

            /** @todo: can do Cache.cleanUp() here, too */
        } catch (Exception e) {

            // ignore exceptions
            // may be InterruptedException or IOException
        }
    }
