    void closeJoinChain(HsqlList[] array, Expression e1, Expression e2) {

        int idx1  = rangeVarSet.getIndex(e1.getRangeVariable());
        int idx2  = rangeVarSet.getIndex(e2.getRangeVariable());
        int index = idx1 > idx2 ? idx1
                                : idx2;

        if (idx1 == -1 || idx2 == -1) {
            return;
        }

        Expression e = new ExpressionLogical(e1, e2);

        for (int i = 0; i < array[index].size(); i++) {
            if (e.equals(array[index].get(i))) {
                return;
            }
        }

        array[index].add(e);
    }
