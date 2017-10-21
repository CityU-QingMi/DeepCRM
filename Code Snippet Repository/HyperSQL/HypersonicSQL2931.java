    private static void getColumnList(Table t, OrderedHashSet set,
                                      StringBuffer buf) {

        int       count        = 0;
        boolean[] colCheckList = t.getNewColumnCheckList();

        for (int i = 0; i < set.size(); i++) {
            HsqlName name     = (HsqlName) set.get(i);
            int      colIndex = t.findColumn(name.name);

            if (colIndex == -1) {
                continue;
            }

            colCheckList[colIndex] = true;

            count++;
        }

        if (count == 0) {
            throw Error.runtimeError(ErrorCode.U_S0500, "Right");
        }

        buf.append('(');

        for (int i = 0, colCount = 0; i < colCheckList.length; i++) {
            if (colCheckList[i]) {
                colCount++;

                buf.append(t.getColumn(i).getName().statementName);

                if (colCount < count) {
                    buf.append(',');
                }
            }
        }

        buf.append(')');
    }
