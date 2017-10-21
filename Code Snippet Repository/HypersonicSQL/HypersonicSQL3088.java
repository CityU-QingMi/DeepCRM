    public void writeTableInit(Table t) throws IOException {

        if (t.isEmpty(currentSession)) {
            return;
        }

        if (!includeTableInit && schemaToLog == currentSession.loggedSchema) {
            return;
        }

        rowOut.reset();
        writeSchemaStatement(t.getName().schema);
        writeRowOutToFile();

        currentSession.loggedSchema = schemaToLog;
    }
