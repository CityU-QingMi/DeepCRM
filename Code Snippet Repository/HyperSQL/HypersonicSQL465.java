    ColumnSchema readSimpleColumnName(RangeVariable rangeVar,
                                      boolean withPrefix) {

        ColumnSchema column = null;

        checkIsIdentifier();

        if (!withPrefix && token.namePrefix != null) {
            throw tooManyIdentifiers();
        }

        int index = rangeVar.findColumn(token.namePrePrefix, token.namePrefix,
                                        token.tokenString);

        if (index == -1) {
            throw Error.error(ErrorCode.X_42501, token.tokenString);
        }

        column = rangeVar.getTable().getColumn(index);

        read();

        return column;
    }
