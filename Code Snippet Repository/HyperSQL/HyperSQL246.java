    public boolean canRead(Session session, PersistentStore store, long id,
                           int mode) {

        if (store.getTable().tableType == TableBase.TEMP_TABLE) {
            return true;
        }

        RowAction action = (RowAction) rowActionMap.get(id);

        if (action == null) {
            return true;
        }

        return action.canRead(session, mode);
    }
