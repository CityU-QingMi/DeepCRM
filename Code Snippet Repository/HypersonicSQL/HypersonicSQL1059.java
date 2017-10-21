    void setColumnStructures() {

        if (colTypes == null) {
            colTypes = new Type[columnCount];
        }

        colDefaults          = new Expression[columnCount];
        colNotNull           = new boolean[columnCount];
        emptyColumnCheckList = new boolean[columnCount];
        colGenerated         = new boolean[columnCount];
        colUpdated           = new boolean[columnCount];
        defaultColumnMap     = new int[columnCount];

        for (int i = 0; i < columnCount; i++) {
            setSingleColumnTypeVars(i);
        }

        resetDefaultsFlag();
    }
