    public long nonSpaceLength(SessionInterface session) {

        ResultLob resultOut = ResultLob.newLobGetTruncateLength(id);
        Result    resultIn  = session.execute(resultOut);

        if (resultIn.isError()) {
            throw resultIn.getException();
        }

        return ((ResultLob) resultIn).getBlockLength();
    }
