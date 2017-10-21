    public OdbcPreparedStatement(String handle, String query,
                                 Map containingMap,
                                 Session session)
                                 throws RecoverableOdbcFailure {

        this.handle        = handle;
        this.query         = query;
        this.containingMap = containingMap;
        this.session       = session;

        Result psResult = Result.newPrepareStatementRequest();

        psResult.setPrepareOrExecuteProperties(
            query, 0, 0, 0, 0,ResultProperties.defaultPropsValue,
            Statement.NO_GENERATED_KEYS, null, null);

        ackResult = session.execute(psResult);

        switch (ackResult.getType()) {

            case ResultConstants.PREPARE_ACK :
                break;

            case ResultConstants.ERROR :
                throw new RecoverableOdbcFailure(ackResult);
            default :
                throw new RecoverableOdbcFailure(
                    "Output Result from Statement prep is of "
                    + "unexpected type: " + ackResult.getType());
        }

        containingMap.put(handle, this);
    }
