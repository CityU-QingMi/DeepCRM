    public boolean checkComplexity(Session session, String password) {

        if (session == null || pwCheckFunction == null) {
            return true;
        }

        Result result = pwCheckFunction.invoke(session,
                                               new Object[]{ password }, null,
                                               true);
        Boolean check = (Boolean) result.getValueObject();

        if (check == null || !check.booleanValue()) {
            return false;
        }

        return true;
    }
