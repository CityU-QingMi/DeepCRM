    public final void notify(int id) {

        printWithThread("notify( database shutdown," + id + ") entered");
        releaseDatabase(id);

        boolean shutdown = true;

        for (int i = 0; i < dbID.length; i++) {
            if (dbAlias[i] != null) {
                shutdown = false;
            }
        }

        if (!isRemoteOpen && shutdown) {
            stop();
        }
    }
