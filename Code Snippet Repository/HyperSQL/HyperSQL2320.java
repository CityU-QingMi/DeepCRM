    public void writeLogStatement(Session session,
                                  String s) throws IOException {

        if (session != null) {
            schemaToLog = session.currentSchema;

            writeSessionIdAndSchema(session);
        }

        rowOut.reset();
        rowOut.writeString(s);
        rowOut.write(BYTES_LINE_SEP);
        writeRowOutToFile();

        needsSync = true;
    }
