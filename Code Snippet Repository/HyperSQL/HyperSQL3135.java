    Expression getRowExpression(OrderedHashSet columnNames) {

        Expression[] elements = new Expression[columnNames.size()];

        for (int i = 0; i < elements.length; i++) {
            String name = (String) columnNames.get(i);

            elements[i] = new ExpressionColumn(null, null, name);
        }

        return new Expression(OpTypes.ROW, elements);
    }
