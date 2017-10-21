    public void writeCommitStatement(Session session) throws IOException {

        writeSessionIdAndSchema(session);
        rowOut.reset();
        rowOut.write(BYTES_COMMIT);
        rowOut.write(BYTES_LINE_SEP);
        writeRowOutToFile();

        needsSync = true;

        if (writeDelay == 0) {
            sync();
        }
    }
