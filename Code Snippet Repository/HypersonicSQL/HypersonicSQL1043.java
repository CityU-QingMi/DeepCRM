    void checkColumnInFKConstraint(int colIndex) {

        for (int i = 0, size = constraintList.length; i < size; i++) {
            Constraint c = constraintList[i];

            if (c.hasColumn(colIndex) && (c.getConstraintType() == SchemaObject
                    .ConstraintTypes.MAIN || c
                    .getConstraintType() == SchemaObject.ConstraintTypes
                    .FOREIGN_KEY)) {
                HsqlName name = c.getName();

                throw Error.error(ErrorCode.X_42533,
                                  name.getSchemaQualifiedStatementName());
            }
        }
    }
