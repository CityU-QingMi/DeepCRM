    public long position(SessionInterface session, byte[] pattern,
                         long start) {

        ResultLob resultOut = ResultLob.newLobGetBytePatternPositionRequest(id,
            pattern, start);
        Result resultIn = session.execute(resultOut);

        if (resultIn.isError()) {
            throw resultIn.getException();
        }

        return ((ResultLob) resultIn).getOffset();
    }
