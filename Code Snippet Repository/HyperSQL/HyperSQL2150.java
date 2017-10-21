    public void setColumns(Table table) {

        if (selectColumnSet != null) {
            setColumns(table, selectColumnSet);
        }

        if (insertColumnSet != null) {
            setColumns(table, insertColumnSet);
        }

        if (updateColumnSet != null) {
            setColumns(table, updateColumnSet);
        }

        if (referencesColumnSet != null) {
            setColumns(table, referencesColumnSet);
        }

        if (triggerColumnSet != null) {
            setColumns(table, triggerColumnSet);
        }
    }
