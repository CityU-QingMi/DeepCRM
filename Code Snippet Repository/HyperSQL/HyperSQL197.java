    public void rollbackPartial(Session session, int start, long timestamp) {

        int limit = session.rowActionList.size();

        if (start == limit) {
            return;
        }

        for (int i = limit - 1; i >= start; i--) {
            RowAction action = (RowAction) session.rowActionList.get(i);

            if (action == null || action.type == RowActionBase.ACTION_NONE
                    || action.type == RowActionBase.ACTION_DELETE_FINAL) {
                continue;
            }

            Row row = action.memoryRow;

            if (row == null) {
                row = (Row) action.store.get(action.getPos(), false);
            }

            if (row == null) {
                continue;
            }

            action.rollback(session, timestamp);

            int type = action.mergeRollback(session, timestamp, row);

            action.store.rollbackRow(session, row, type, txModel);
        }

        session.rowActionList.setSize(start);
    }
