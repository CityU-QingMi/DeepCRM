    @Override
    public boolean execute() throws IOException {
        if (stopOnError) {
            for (final Action action : actions) {
                if (!action.execute()) {
                    return false;
                }
            }

            return true;
        }
        boolean status = true;
        IOException exception = null;

        for (final Action action : actions) {
            try {
                status &= action.execute();
            } catch (final IOException ex) {
                status = false;

                if (exception == null) {
                    exception = ex;
                }
            }
        }

        if (exception != null) {
            throw exception;
        }

        return status;
    }
