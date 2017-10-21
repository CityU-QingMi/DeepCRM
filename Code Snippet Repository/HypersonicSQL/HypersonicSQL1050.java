    void checkColumnInFKConstraint(int colIndex, int actionType) {

        for (int i = 0, size = constraintList.length; i < size; i++) {
            Constraint c = constraintList[i];

            if (c.getConstraintType() == SchemaObject.ConstraintTypes
                    .FOREIGN_KEY && c
                    .hasColumn(colIndex) && (actionType == c
                        .getUpdateAction() || actionType == c
                        .getDeleteAction())) {
                HsqlName name = c.getName();

                throw Error.error(ErrorCode.X_42533,
                                  name.getSchemaQualifiedStatementName());
            }
        }
    }
