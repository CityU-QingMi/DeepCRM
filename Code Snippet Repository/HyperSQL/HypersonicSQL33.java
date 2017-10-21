    public synchronized Object getAttribute(int id) {

        resultOut.setResultType(ResultConstants.GETSESSIONATTR);
        resultOut.setStatementType(id);

        Result in = execute(resultOut);

        if (in.isError()) {
            throw Error.error(in);
        }

        Object[] data = in.getSingleRowData();

        switch (id) {

            case SessionInterface.INFO_AUTOCOMMIT :
                return data[SessionInterface.INFO_BOOLEAN];

            case SessionInterface.INFO_CONNECTION_READONLY :
                return data[SessionInterface.INFO_BOOLEAN];

            case SessionInterface.INFO_ISOLATION :
                return data[SessionInterface.INFO_INTEGER];

            case SessionInterface.INFO_CATALOG :
                return data[SessionInterface.INFO_VARCHAR];
        }

        return null;
    }
