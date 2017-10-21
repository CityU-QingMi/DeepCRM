    void persistCommit(Session session) {

        int     limit       = session.rowActionList.size();
        boolean writeCommit = false;

        for (int i = 0; i < limit; i++) {
            RowAction action = (RowAction) session.rowActionList.get(i);

            if (action.type == RowActionBase.ACTION_NONE) {
                continue;
            }

            int type = action.getCommitTypeOn(session.actionTimestamp);
            Row row  = action.memoryRow;

            if (row == null) {
                row = (Row) action.store.get(action.getPos(), false);
            }

            if (action.table.tableType != TableBase.TEMP_TABLE) {
                writeCommit = true;
            }

            try {
                action.store.commitRow(session, row, type, txModel);

                if (txModel == TransactionManager.LOCKS
                        || action.table.tableType == TableBase.TEMP_TABLE) {
                    action.setAsNoOp();

                    row.rowAction = null;
                }
            } catch (HsqlException e) {
                database.logger.logWarningEvent("data commit failed", e);
            }
        }

        try {
            session.logSequences();

            if (limit > 0 && writeCommit) {
                database.logger.writeCommitStatement(session);
            }
        } catch (HsqlException e) {
            database.logger.logWarningEvent("data commit logging failed", e);
        }
    }
