    public void setString(SessionInterface session, long pos, String str) {

        ResultLob resultOut = ResultLob.newLobSetCharsRequest(id, pos,
            str.toCharArray());
        Result resultIn = session.execute(resultOut);

        if (resultIn.isError()) {
            throw resultIn.getException();
        }

        this.length = ((ResultLob) resultIn).getBlockLength();
    }
