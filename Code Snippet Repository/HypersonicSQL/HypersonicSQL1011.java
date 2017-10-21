    public void checkIsNotColumnTarget() {

        for (int i = 0; i < targets.length; i++) {
            ColumnSchema col = targets[i].getColumn();

            if (col.getType() == SchemaObject.COLUMN) {
                throw Error.error(ErrorCode.X_0U000,
                                  col.getName().statementName);
            }
        }
    }
