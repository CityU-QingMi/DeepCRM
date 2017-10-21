    public boolean canRead(Session session, CachedObject object, int mode,
                           int[] colMap) {

        RowAction action = ((Row) object).rowAction;

        if (action == null) {
            return true;
        }

        return action.canRead(session, mode);
    }
