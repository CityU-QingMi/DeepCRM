    String getAlias() {

        if (alias != null) {
            return alias.name;
        }

        switch (opType) {

            case OpTypes.COLUMN :
            case OpTypes.COALESCE :
            case OpTypes.ROWNUM :
                return columnName;
        }

        return "";
    }
