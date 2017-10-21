    public void prepareExtraColumn(int degree) {

        columnCount = exprList.size();

        if (columnCount == 0) {
            return;
        }

        sortOrder      = new int[columnCount + degree];
        sortDescending = new boolean[columnCount + degree];
        sortNullsLast  = new boolean[columnCount + degree];

        ArrayUtil.fillSequence(sortOrder);

        for (int i = 0; i < columnCount; i++) {
            ExpressionOrderBy sort = (ExpressionOrderBy) exprList.get(i);

            sortDescending[i] = sort.isDescending();
            sortNullsLast[i]  = sort.isNullsLast();
            hasNullsLast      |= sortNullsLast[i];
        }
    }
