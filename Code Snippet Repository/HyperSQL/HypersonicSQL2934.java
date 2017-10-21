    private static void setColumns(Table t, OrderedHashSet set) {

        int       count        = 0;
        boolean[] colCheckList = t.getNewColumnCheckList();

        for (int i = 0; i < set.size(); i++) {
            String name     = (String) set.get(i);
            int    colIndex = t.findColumn(name);

            if (colIndex == -1) {
                throw Error.error(ErrorCode.X_42501, name);
            }

            colCheckList[colIndex] = true;

            count++;
        }

        if (count == 0) {
            throw Error.error(ErrorCode.X_42501);
        }

        set.clear();

        for (int i = 0; i < colCheckList.length; i++) {
            if (colCheckList[i]) {
                set.add(t.getColumn(i).getName());
            }
        }
    }
