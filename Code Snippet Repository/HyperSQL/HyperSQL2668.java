    public synchronized void setAttribute(int id, Object value) {

        resultOut.setResultType(ResultConstants.SETSESSIONATTR);

        Object[] data = resultOut.getSingleRowData();

        data[SessionInterface.INFO_ID] = ValuePool.getInt(id);

        switch (id) {

            case SessionInterface.INFO_AUTOCOMMIT :
            case SessionInterface.INFO_CONNECTION_READONLY :
                data[SessionInterface.INFO_BOOLEAN] = value;
                break;

            case SessionInterface.INFO_ISOLATION :
                data[SessionInterface.INFO_INTEGER] = value;
                break;

            case SessionInterface.INFO_CATALOG :
                data[SessionInterface.INFO_VARCHAR] = value;
                break;

            default :
        }

        Result resultIn = execute(resultOut);

        if (resultIn.isError()) {
            throw Error.error(resultIn);
        }
    }
