    void checkCheckConstraint(Session session, Table table, Object[] data) {

        RangeIterator it =
            session.sessionContext.getCheckIterator(rangeVariable);

        it.setCurrent(data);

        boolean nomatch = Boolean.FALSE.equals(check.getValue(session));

        if (nomatch) {
            String[] info = new String[] {
                name.name, table.getName().name
            };

            throw Error.error(null, ErrorCode.X_23513, ErrorCode.CONSTRAINT,
                              info);
        }
    }
