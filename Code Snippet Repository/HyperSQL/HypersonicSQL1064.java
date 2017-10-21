    String[] getSQL(OrderedHashSet resolved, OrderedHashSet unresolved) {

        for (int i = 0; i < constraintList.length; i++) {
            Constraint c = constraintList[i];

            if (c.isForward) {
                unresolved.add(c);
            } else if (c.getConstraintType() == SchemaObject.ConstraintTypes
                    .UNIQUE || c.getConstraintType() == SchemaObject
                    .ConstraintTypes.PRIMARY_KEY) {
                resolved.add(c.getName());
            }
        }

        HsqlArrayList list = new HsqlArrayList();

        list.add(getSQL());

        if (!isTemp && !isText && identitySequence != null
                && identitySequence.getName() == null) {
            list.add(NumberSequence.getRestartSQL(this));
        }

        for (int i = 0; i < indexList.length; i++) {
            if (!indexList[i].isConstraint()
                    && indexList[i].getColumnCount() > 0) {
                list.add(indexList[i].getSQL());
            }
        }

        String[] array = new String[list.size()];

        list.toArray(array);

        return array;
    }
