    void readSimpleColumnNames(OrderedHashSet columns, RangeVariable rangeVar,
                               boolean withPrefix) {

        while (true) {
            ColumnSchema col = readSimpleColumnName(rangeVar, withPrefix);

            if (!columns.add(col.getName().name)) {
                throw Error.error(ErrorCode.X_42579, col.getName().name);
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
