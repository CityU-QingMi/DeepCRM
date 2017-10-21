    public long position(SessionInterface session, BlobData pattern,
                         long start) {

        ResultLob resultOut = ResultLob.newLobGetCharPatternPositionRequest(id,
            pattern.getId(), start);
        Result resultIn = session.execute(resultOut);

        if (resultIn.isError()) {
            throw resultIn.getException();
        }

        return ((ResultLob) resultIn).getOffset();
    }
