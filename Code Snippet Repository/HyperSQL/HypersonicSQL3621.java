    public long position(SessionInterface session, ClobData searchstr,
                         long start) {

        ResultLob resultOut = ResultLob.newLobGetCharPatternPositionRequest(id,
            searchstr.getId(), start);
        Result resultIn = session.execute(resultOut);

        if (resultIn.isError()) {
            throw resultIn.getException();
        }

        return ((ResultLob) resultIn).getOffset();
    }
