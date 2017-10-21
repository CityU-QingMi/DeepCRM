    public void addNamedJoinColumnExpression(String name, Expression e,
            int position) {

        if (namedJoinColumnExpressions == null) {
            namedJoinColumnExpressions = new HashMap();
        }

        namedJoinColumnExpressions.put(name, e);

        if (namedJoinColumnCheck == null) {
            namedJoinColumnCheck = rangeTable.getNewColumnCheckList();
        }

        namedJoinColumnCheck[position] = true;
    }
