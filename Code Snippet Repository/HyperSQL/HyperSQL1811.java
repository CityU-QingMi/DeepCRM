    void writeOtherStatement(Session session, String s) {

        try {
            dbLogWriter.writeOtherStatement(session, s);
        } catch (IOException e) {
            throw Error.error(ErrorCode.FILE_IO_ERROR, getLogFileName());
        }

        if (maxLogSize > 0 && dbLogWriter.size() > maxLogSize) {
            database.logger.setCheckpointRequired();
        }

        setModified();
    }
