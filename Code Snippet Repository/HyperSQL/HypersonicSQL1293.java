    public void closeReader() {
        if (reader == null) return;
        try {
            if (scanner != null) try {
                scanner.yyclose();
            } catch (IOException ioe) {
                errprintln(SqltoolRB.pipeclose_failure.getString(ioe));
            }
            try {
                reader.close();
            } catch (IOException ioe) {
                // Purposefully empty.
                // The reader will usually already be closed at this point.
            }
        } finally {
            reader = null; // Encourage GC of buffers
        }
    }
