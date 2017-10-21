    public OrderedHashSet getUniquePKConstraintNames() {

        OrderedHashSet set = new OrderedHashSet();

        for (int i = 0, size = constraintList.length; i < size; i++) {
            Constraint c = constraintList[i];

            if (c.getConstraintType() == SchemaObject.ConstraintTypes.UNIQUE
                    || c.getConstraintType()
                       == SchemaObject.ConstraintTypes.PRIMARY_KEY) {
                set.add(c.getName());
            }
        }

        return set;
    }
