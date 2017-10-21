    public void writeSequenceStatement(Session session,
                                       NumberSequence seq) throws IOException {

        schemaToLog = seq.getName().schema;

        writeSessionIdAndSchema(session);
        rowOut.reset();
        rowOut.write(BYTES_SEQUENCE);
        rowOut.writeString(seq.getSchemaName().statementName);
        rowOut.write('.');
        rowOut.writeString(seq.getName().statementName);
        rowOut.write(BYTES_SEQUENCE_MID);
        rowOut.writeLong(seq.peek());
        rowOut.write(BYTES_LINE_SEP);
        writeRowOutToFile();

        needsSync = true;
    }
