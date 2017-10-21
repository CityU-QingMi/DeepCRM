    @Override
    public synchronized void run() {
        if (!interrupted) {
            try {
                execute();
            } catch (final IOException ex) {
                reportException(ex);
            }

            complete = true;
            interrupted = true;
        }
    }
