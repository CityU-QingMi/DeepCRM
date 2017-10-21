    public OrderedHashSet getColumnsForAllPrivileges(SchemaObject object) {

        if (object instanceof Table) {
            Table table = (Table) object;

            if (isFullyAccessibleByRole(table.getName())) {
                return table.getColumnNameSet();
            }

            Right right = (Right) fullRightsMap.get(table.getName());

            return right == null ? Right.emptySet
                                 : right.getColumnsForAllRights(table);
        }

        return Right.emptySet;
    }
