    void renameColumn(ColumnSchema column, HsqlName newName) {

        String oldname = column.getName().name;
        int    i       = getColumnIndex(oldname);

        if (findColumn(newName.name) != -1) {
            throw Error.error(ErrorCode.X_42504);
        }

        columnList.setKey(i, newName.name);
        column.getName().rename(newName);
    }
