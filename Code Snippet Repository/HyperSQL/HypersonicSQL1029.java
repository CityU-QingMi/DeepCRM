    public void addConstraint(Constraint c) {

        int index = c.getConstraintType()
                    == SchemaObject.ConstraintTypes.PRIMARY_KEY ? 0
                                                                : constraintList
                                                                    .length;

        constraintList =
            (Constraint[]) ArrayUtil.toAdjustedArray(constraintList, c, index,
                1);

        updateConstraintLists();
    }
