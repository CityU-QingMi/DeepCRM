    private Boolean testNotDistinctCondition(Session session) {

        Object[] leftData  = nodes[LEFT].getRowValue(session);
        Object[] rightData = nodes[RIGHT].getRowValue(session);

        if (leftData == null || rightData == null) {
            return leftData == rightData;
        }

        return compareValues(session, leftData, rightData);
    }
