    public void setUpdatedColumns(Session session, Object[] data) {

        if (hasUpdatedValues) {
            for (int i = 0; i < colUpdated.length; i++) {
                if (colUpdated[i]) {
                    Expression e = getColumn(i).getUpdateExpression();

                    data[i] = e.getValue(session, colTypes[i]);
                }
            }
        }
    }
