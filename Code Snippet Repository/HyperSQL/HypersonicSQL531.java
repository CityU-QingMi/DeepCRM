    public Object[] getSingleRowValues(Session session) {

        Result r    = getResult(session, 2);
        int    size = r.getNavigator().getSize();

        if (size == 0) {
            return null;
        } else if (size == 1) {
            return r.getSingleRowData();
        } else {
            throw Error.error(ErrorCode.X_21000);
        }
    }
