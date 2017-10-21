    public void prepare(int startColumn) {

        columnCount = exprList.size();

        if (columnCount == 0) {
            return;
        }

        sortOrder      = new int[columnCount];
        sortDescending = new boolean[columnCount];
        sortNullsLast  = new boolean[columnCount];

        for (int i = 0; i < columnCount; i++) {
            ExpressionOrderBy sort = (ExpressionOrderBy) exprList.get(i);

            if (sort.getLeftNode().queryTableColumnIndex == -1) {
                sortOrder[i] = startColumn + i;
            } else {
                sortOrder[i] = sort.getLeftNode().queryTableColumnIndex;
            }

            sortDescending[i] = sort.isDescending();
            sortNullsLast[i]  = sort.isNullsLast();
            hasNullsLast      |= sortNullsLast[i];

            if (sort.collation != null) {
                if (collations == null) {
                    collations = new Collation[columnCount];
                }

                collations[i] = sort.collation;
            }
        }
    }
