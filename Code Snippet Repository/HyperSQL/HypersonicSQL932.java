    private void initialiseVariables(Session session) {

        Object[] vars   = session.sessionContext.routineVariables;
        int      offset = parent == null ? 0
                                         : parent.scopeVariables.size();

        for (int i = 0; i < variables.length; i++) {
            try {
                vars[offset + i] = variables[i].getDefaultValue(session);
            } catch (HsqlException e) {}
        }
    }
