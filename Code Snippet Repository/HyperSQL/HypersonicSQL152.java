    public Expression duplicate() {

        ExpressionArrayAggregate e =
            (ExpressionArrayAggregate) super.duplicate();

        if (condition != null) {
            e.condition = condition.duplicate();
        }

        return e;
    }
