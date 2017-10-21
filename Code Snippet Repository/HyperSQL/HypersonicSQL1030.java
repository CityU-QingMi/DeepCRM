    void verifyConstraintsIntegrity() {

        for (int i = 0; i < constraintList.length; i++) {
            Constraint c = constraintList[i];

            if (c.getConstraintType() == SchemaObject.ConstraintTypes
                    .FOREIGN_KEY || c.getConstraintType() == SchemaObject
                    .ConstraintTypes.MAIN) {
                if (c.getMain()
                        != database.schemaManager.findUserTable(
                            c.getMain().getName().name,
                            c.getMain().getName().schema.name)) {
                    throw Error.runtimeError(ErrorCode.U_S0500,
                                             "FK mismatch : "
                                             + c.getName().name);
                }

                if (c.getRef()
                        != database.schemaManager.findUserTable(
                            c.getRef().getName().name,
                            c.getRef().getName().schema.name)) {
                    throw Error.runtimeError(ErrorCode.U_S0500,
                                             "FK mismatch : "
                                             + c.getName().name);
                }
            }
        }
    }
