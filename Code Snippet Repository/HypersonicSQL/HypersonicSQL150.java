    public boolean equals(Expression other) {

        if (other instanceof ExpressionArrayAggregate) {
            ExpressionArrayAggregate o = (ExpressionArrayAggregate) other;

            return super.equals(other) && opType == other.opType
                   && exprSubType == other.exprSubType
                   && isDistinctAggregate == o.isDistinctAggregate
                   && separator.equals(o.separator)
                   && condition.equals(o.condition);
        }

        return false;
    }
