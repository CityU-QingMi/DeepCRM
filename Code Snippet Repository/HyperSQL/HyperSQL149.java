    private void setTableIndexesForSubquery(Session session) {

        int[]   cols         = null;
        boolean hasFullIndex = false;

        if (queryExpression != null) {
            if (queryExpression.fullIndex != null) {
                hasFullIndex = true;
            }
        }

        if (hasFullIndex || uniqueRows || uniquePredicate) {
            cols = new int[getColumnCount()];

            ArrayUtil.fillSequence(cols);
        }

        int[] pkcols = uniqueRows ? cols
                                  : null;

        createPrimaryKey(null, pkcols, false);

        if (uniqueRows) {
            fullIndex = getPrimaryIndex();
        } else if (uniquePredicate || hasFullIndex) {
            fullIndex = createIndexForColumns(session, cols);
        }
    }
