    void updateRightsForNewColumn(HsqlName tableName) {

        Iterator it       = grantedRightsMap.get(tableName);
        Right    existing = null;

        while (it.hasNext()) {
            existing = (Right) it.next();
        }

        if (existing == null) {
            return;
        }

        updateAllRights();
    }
