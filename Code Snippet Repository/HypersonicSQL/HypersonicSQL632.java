    private void collectIndexableColumns(RangeVariable range,
                                         HsqlList exprList) {

        colIndexSetEqual.clear();
        colIndexSetOther.clear();

        for (int j = 0, size = exprList.size(); j < size; j++) {
            Expression e = (Expression) exprList.get(j);

            if (!e.isSingleColumnCondition) {
                continue;
            }

            int idx;

            if (e.getLeftNode().getRangeVariable() == range) {
                idx = e.getLeftNode().getColumnIndex();
            } else if (e.getRightNode().getRangeVariable() == range) {
                idx = e.getRightNode().getColumnIndex();
            } else {
                continue;
            }

            if (e.isSingleColumnEqual) {
                colIndexSetEqual.add(idx);
            } else {
                int count = colIndexSetOther.get(idx, 0);

                colIndexSetOther.put(idx, count + 1);
            }
        }
    }
