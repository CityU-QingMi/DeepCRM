    void checkColumnInCheckConstraint(int colIndex) {

        for (int i = 0, size = constraintList.length; i < size; i++) {
            Constraint c = constraintList[i];

            if (c.getConstraintType() == SchemaObject.ConstraintTypes.CHECK
                    && !c.isNotNull() && c.hasColumn(colIndex)) {
                HsqlName name = c.getName();

                throw Error.error(ErrorCode.X_42502,
                                  name.getSchemaQualifiedStatementName());
            }
        }
    }
