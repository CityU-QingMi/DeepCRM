    private Result executeProtected(Session session, Statement statement) {

        int actionIndex = session.rowActionList.size();

        session.actionTimestamp =
            session.database.txManager.getNextGlobalChangeTimestamp();

        Result result = statement.execute(session);

        if (result.isError()) {
            session.rollbackAction(actionIndex, session.actionTimestamp);
        }

        return result;
    }
