    public boolean hasNonSelectTableRight(SchemaObject table) {

        if (isFullyAccessibleByRole(table.getName())) {
            return true;
        }

        Right right = (Right) fullRightsMap.get(table.getName());

        if (right == null) {
            return false;
        }

        return right.canAccesssNonSelect();
    }
