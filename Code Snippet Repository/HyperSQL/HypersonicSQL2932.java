    public void addNewColumn(HsqlName name) {

        if (selectColumnSet != null) {
            selectColumnSet.add(name);
        }

        if (insertColumnSet != null) {
            insertColumnSet.add(name);
        }

        if (updateColumnSet != null) {
            updateColumnSet.add(name);
        }

        if (referencesColumnSet != null) {
            referencesColumnSet.add(name);
        }

        if (triggerColumnSet != null) {
            triggerColumnSet.add(name);
        }
    }
