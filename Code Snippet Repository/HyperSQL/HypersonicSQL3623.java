    public long length(SessionInterface session) {

        if (length > -1) {
            return length;
        }

        ResultLob resultOut = ResultLob.newLobGetLengthRequest(id);
        Result    resultIn  = session.execute(resultOut);

        if (resultIn.isError()) {
            throw resultIn.getException();
        }

        length = ((ResultLob) resultIn).getBlockLength();

        return length;
    }
