    void updateConstraints(Table t, OrderedHashSet dropConstraints) {

        for (int i = t.constraintList.length - 1; i >= 0; i--) {
            Constraint c = t.constraintList[i];

            if (dropConstraints.contains(c.getName())) {
                t.removeConstraint(i);

                continue;
            }

            if (c.getConstraintType()
                    == SchemaObject.ConstraintTypes.FOREIGN_KEY) {
                Table refT = database.schemaManager.getUserTable(
                    c.core.refTable.getName());

                c.core.refTable = refT;

                Table mainT = database.schemaManager.getUserTable(
                    c.core.mainTable.getName());
                Constraint mainC = mainT.getConstraint(c.getMainName().name);

                mainC.core = c.core;
            } else if (c.getConstraintType()
                       == SchemaObject.ConstraintTypes.MAIN) {
                Table mainT = database.schemaManager.getUserTable(
                    c.core.mainTable.getName());

                c.core.mainTable = mainT;

                Table refT = database.schemaManager.getUserTable(
                    c.core.refTable.getName());
                Constraint refC = refT.getConstraint(c.getRefName().name);

                refC.core = c.core;
            }
        }
    }
