    public void truncate(SessionInterface session, long len) {

        if (len >= length(session)) {
            return;
        }

        ResultLob resultOut = ResultLob.newLobTruncateRequest(id, len);
        Result    resultIn  = session.execute(resultOut);

        if (resultIn.isError()) {
            throw resultIn.getException();
        }
    }
