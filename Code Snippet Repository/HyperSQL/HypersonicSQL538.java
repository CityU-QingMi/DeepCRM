    private void resolveColumnReferencesForAsterisk() {

        for (int pos = 0; pos < indexLimitVisible; ) {
            Expression e = (Expression) (exprColumnList.get(pos));

            if (e.getType() == OpTypes.MULTICOLUMN) {
                exprColumnList.remove(pos);

                String tablename = ((ExpressionColumn) e).getTableName();

                if (tablename == null) {
                    addAllJoinedColumns(e);
                } else {
                    boolean resolved = false;

                    for (int i = 0; i < rangeVariables.length; i++) {
                        RangeVariable range =
                            rangeVariables[i].getRangeForTableName(tablename);

                        if (range != null) {
                            HashSet exclude = getAllNamedJoinColumns();

                            rangeVariables[i].addTableColumns(range, e,
                                                              exclude);

                            resolved = true;

                            break;
                        }
                    }

                    if (!resolved) {
                        throw Error.error(ErrorCode.X_42501, tablename);
                    }
                }

                for (int i = 0; i < e.nodes.length; i++) {
                    exprColumnList.add(pos, e.nodes[i]);

                    pos++;
                }

                indexLimitVisible += e.nodes.length - 1;
            } else {
                pos++;
            }
        }
    }
