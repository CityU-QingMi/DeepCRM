    void adjustLobUsage(Session session) {

        int  limit               = session.rowActionList.size();
        long lastActionTimestamp = session.actionTimestamp;

        for (int i = 0; i < limit; i++) {
            RowAction action = (RowAction) session.rowActionList.get(i);

            if (action.type == RowActionBase.ACTION_NONE) {
                continue;
            }

            if (action.table.hasLobColumn) {
                int type = action.getCommitTypeOn(lastActionTimestamp);
                Row row  = action.memoryRow;

                if (row == null) {
                    row = (Row) action.store.get(action.getPos(), false);
                }

                switch (type) {

                    case RowActionBase.ACTION_INSERT :
                        session.sessionData.adjustLobUsageCount(action.table,
                                row.getData(), 1);
                        break;

                    case RowActionBase.ACTION_DELETE :
                        session.sessionData.adjustLobUsageCount(action.table,
                                row.getData(), -1);
                        break;

                    case RowActionBase.ACTION_INSERT_DELETE :
                    default :
                }
            }
        }

        int newLimit = session.rowActionList.size();

        if (newLimit > limit) {
            for (int i = limit; i < newLimit; i++) {
                RowAction lobAction = (RowAction) session.rowActionList.get(i);

                lobAction.commit(session);
            }
        }
    }
