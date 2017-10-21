    public boolean hasColumnRights(SchemaObject table, int[] columnMap) {

        if (isFullyAccessibleByRole(table.getName())) {
            return true;
        }

        Right right = (Right) fullRightsMap.get(table.getName());

        if (right == null) {
            return false;
        }

        return right.canAccess((Table) table, columnMap);
    }
