    public Constraint getUniqueConstraintForIndex(Index index) {

        for (int i = 0, size = constraintList.length; i < size; i++) {
            Constraint c = constraintList[i];

            if (c.getMainIndex() == index) {
                if (c.getConstraintType() == SchemaObject.ConstraintTypes
                        .PRIMARY_KEY || c.getConstraintType() == SchemaObject
                        .ConstraintTypes.UNIQUE) {
                    return c;
                }
            }
        }

        return null;
    }
