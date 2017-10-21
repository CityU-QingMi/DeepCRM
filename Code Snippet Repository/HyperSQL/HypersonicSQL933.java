    private void initialiseVariables(Session session, Object[] data,
                                     int count) {

        Object[] vars = session.sessionContext.routineVariables;

        for (int i = 0; i < count; i++) {
            try {
                vars[variablesOffset + i] = data[i];
            } catch (HsqlException e) {}
        }
    }
