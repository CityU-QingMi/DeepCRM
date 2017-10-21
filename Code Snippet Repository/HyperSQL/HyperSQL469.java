    public boolean equals(Expression other) {

        if (other instanceof ExpressionAggregate) {
            ExpressionAggregate o = (ExpressionAggregate) other;

            return super.equals(other)
                   && isDistinctAggregate == o.isDistinctAggregate;
        }

        return false;
    }
