    Result executeDeleteStatement(Session session, int limit) {

        int count = 0;
        RangeIterator it = RangeVariable.getIterator(session,
            targetRangeVariables);
        RowSetNavigatorDataChange rowset =
            session.sessionContext.getRowSetDataChange();

        session.sessionContext.rownum = 1;

        int rowCount = 0;

        while (it.next()) {
            Row currentRow = it.getCurrentRow();

            rowset.addRow(currentRow);

            session.sessionContext.rownum++;
            rowCount++;

            if (rowCount == limit) {
                break;
            }
        }

        it.release();
        rowset.endMainDataSet();

        if (rowset.getSize() > 0) {
            count = delete(session, baseTable, rowset);
        } else {
            session.addWarning(HsqlException.noDataCondition);

            return Result.updateZeroResult;
        }

        if (count == 1) {
            return Result.updateOneResult;
        }

        return new Result(ResultConstants.UPDATECOUNT, count);
    }
