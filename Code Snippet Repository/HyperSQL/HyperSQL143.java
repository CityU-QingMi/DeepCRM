    public Object[] getValues(Session session) {

        RowIterator it = rowIterator(session);

        if (it.next()) {
            Row row = it.getCurrentRow();

            if (it.next()) {
                throw Error.error(ErrorCode.X_21000);
            }

            return row.getData();
        } else {
            return new Object[getColumnCount()];
        }
    }
