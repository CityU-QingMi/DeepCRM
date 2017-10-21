    public ExpressionColumn getColumnExpression(String name) {

        ExpressionColumn col = super.getColumnExpression(name);

        if (col == null) {
            col = rangeArray[0].getColumnExpression(name);
        }

        return col;
    }
