    Result getExpressionResult(Session session) {

        Object o;    // expression return value
        Result r;

        session.sessionData.startRowProcessing();

        o = expression.getValue(session);

        if (resultMetaData == null) {
            getResultMetaData();
        }

        r = Result.newSingleColumnResult(resultMetaData);

        Object[] row;

        if (expression.getDataType().isArrayType()) {
            row    = new Object[1];
            row[0] = o;
        } else if (o instanceof Object[]) {
            row = (Object[]) o;
        } else {
            row    = new Object[1];
            row[0] = o;
        }

        r.getNavigator().add(row);

        return r;
    }
