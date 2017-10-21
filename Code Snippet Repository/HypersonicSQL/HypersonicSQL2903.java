    void updateRightsForNewColumn(HsqlName tableName, HsqlName columnName) {

        Iterator it       = directRightsMap.get(tableName);
        Right    existing = null;

        while (it.hasNext()) {
            existing = (Right) it.next();
        }

        if (existing == null) {
            return;
        }

        existing.addNewColumn(columnName);
        updateAllRights();
    }
