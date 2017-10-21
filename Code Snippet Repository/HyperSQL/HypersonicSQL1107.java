    public void setGeneratedColumns(Session session, Object[] data) {

        if (hasGeneratedValues) {
            for (int i = 0; i < colGenerated.length; i++) {
                if (colGenerated[i]) {
                    Expression e = getColumn(i).getGeneratingExpression();
                    RangeIterator range =
                        session.sessionContext.getCheckIterator(
                            getDefaultRanges()[0]);

                    range.setCurrent(data);

                    data[i] = e.getValue(session, colTypes[i]);
                }
            }
        }
    }
