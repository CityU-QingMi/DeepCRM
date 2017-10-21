    public ClobData duplicate(SessionInterface session) {

        ResultLob resultOut = ResultLob.newLobDuplicateRequest(id);
        Result    resultIn  = session.execute(resultOut);

        if (resultIn.isError()) {
            throw resultIn.getException();
        }

        long lobID = ((ResultLob) resultIn).getLobID();

        return new ClobDataID(lobID);
    }
