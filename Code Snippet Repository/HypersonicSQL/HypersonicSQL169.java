    String getColumnName() {

        switch (opType) {

            case OpTypes.COLUMN :
            case OpTypes.PARAMETER :
            case OpTypes.VARIABLE :
                if (column != null) {
                    return column.getName().name;
                }

                if (columnName != null) {
                    return columnName;
                }
        }

        return getAlias();
    }
