    protected void writeSessionIdAndSchema(Session session)
    throws IOException {

        if (session == null) {
            return;
        }

        if (session != currentSession) {
            rowOut.reset();
            rowOut.write(BYTES_C_ID_INIT);
            rowOut.writeLong(session.getId());
            rowOut.write(BYTES_C_ID_TERM);

            currentSession = session;

            writeRowOutToFile();
        }

        if (schemaToLog != session.loggedSchema) {
            rowOut.reset();
            writeSchemaStatement(schemaToLog);

            session.loggedSchema = schemaToLog;

            writeRowOutToFile();
        }
    }
