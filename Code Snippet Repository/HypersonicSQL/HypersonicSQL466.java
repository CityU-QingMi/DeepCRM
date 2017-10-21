    ColumnSchema readSimpleColumnName(Table table, boolean withPrefix) {

        checkIsIdentifier();

        if (withPrefix) {
            if (token.namePrefix != null
                    && !table.getName().name.equals(token.namePrefix)) {
                throw Error.error(ErrorCode.X_42501, token.namePrefix);
            }
        } else if (token.namePrefix != null) {
            throw tooManyIdentifiers();
        }

        int index = table.findColumn(token.tokenString);

        if (index == -1) {
            throw Error.error(ErrorCode.X_42501, token.tokenString);
        }

        ColumnSchema column = table.getColumn(index);

        read();

        return column;
    }
