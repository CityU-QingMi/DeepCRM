    public Constraint getUniqueOrPKConstraintForIndex(Index index) {

        for (int i = 0, size = constraintList.length; i < size; i++) {
            Constraint c = constraintList[i];

            if (c.getMainIndex() == index && (c
                    .getConstraintType() == SchemaObject.ConstraintTypes
                    .UNIQUE || c.getConstraintType() == SchemaObject
                    .ConstraintTypes.PRIMARY_KEY)) {
                return c;
            }
        }

        return null;
    }
