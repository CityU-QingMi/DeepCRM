    void readGetClauseList(RangeVariable[] rangeVars, OrderedHashSet targets,
                           LongDeque colIndexList, HsqlArrayList expressions) {

        while (true) {
            Expression target = XreadTargetSpecification(rangeVars,
                colIndexList);

            if (!targets.add(target)) {
                ColumnSchema col = target.getColumn();

                throw Error.error(ErrorCode.X_42579, col.getName().name);
            }

            readThis(Tokens.EQUALS_OP);

            switch (token.tokenType) {

                case Tokens.ROW_COUNT :
                case Tokens.MORE :
                    int columnIndex =
                        ExpressionColumn.diagnosticsList.getIndex(
                            token.tokenString);
                    Expression e =
                        new ExpressionColumn(OpTypes.DIAGNOSTICS_VARIABLE,
                                             columnIndex);

                    expressions.add(e);
                    read();
                    break;

                default :
            }

            if (token.tokenType == Tokens.COMMA) {
                read();

                continue;
            }

            break;
        }
    }
