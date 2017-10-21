    public synchronized void setReadOnlyDefault(boolean mode) {

        if (mode != isReadOnlyDefault) {
            setAttribute(SessionInterface.INFO_CONNECTION_READONLY,
                         mode ? Boolean.TRUE
                              : Boolean.FALSE);

            isReadOnlyDefault = mode;
        }
    }
