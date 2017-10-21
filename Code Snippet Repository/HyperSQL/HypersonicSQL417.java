    void readSimpleColumnNames(OrderedHashSet columns, Table table,
                               boolean withPrefix) {

        while (true) {
            ColumnSchema col = readSimpleColumnName(table, withPrefix);

            if (!columns.add(col.getName().name)) {
                throw Error.error(ErrorCode.X_42577, col.getName().name);
            }

            if (readIfThis(Tokens.COMMA)) {
                continue;
            }

            if (token.tokenType == Tokens.CLOSEBRACKET) {
                break;
            }

            throw unexpectedToken();
        }
    }
