    public ClobData getClob(SessionInterface session, long position,
                            long length) {

        ResultLob resultOut = ResultLob.newLobGetRequest(id, position, length);
        Result    resultIn  = session.execute(resultOut);

        if (resultIn.isError()) {
            throw resultIn.getException();
        }

        long lobID = ((ResultLob) resultIn).getLobID();

        return new ClobDataID(lobID);
    }
