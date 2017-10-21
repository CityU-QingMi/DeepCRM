    public void addColumn(ColumnSchema column) {

        String name = column.getName().name;

        if (findColumn(name) >= 0) {
            throw Error.error(ErrorCode.X_42504, name);
        }

        if (column.isIdentity()) {
            if (identityColumn != -1) {
                throw Error.error(ErrorCode.X_42525, name);
            }

            identityColumn   = columnCount;
            identitySequence = column.getIdentitySequence();
        }

        addColumnNoCheck(column);
    }
