    public static Result newConnectionAcknowledgeResponse(Session session) {

        Result result = newResult(ResultConstants.CONNECTACKNOWLEDGE);

        result.sessionID    = session.getId();
        result.databaseID   = session.getDatabase().getDatabaseID();
        result.databaseName = session.getDatabase().getNameString();
        result.mainString =
            session.getDatabase().getProperties()
                .getClientPropertiesAsString();
        result.generateKeys = session.getRandomId();

        return result;
    }
