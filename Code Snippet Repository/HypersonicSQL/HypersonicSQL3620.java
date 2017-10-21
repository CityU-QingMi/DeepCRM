    public char[] getChars(SessionInterface session, long position,
                           int length) {

        ResultLob resultOut = ResultLob.newLobGetCharsRequest(id, position,
            length);
        Result resultIn = session.execute(resultOut);

        if (resultIn.isError()) {
            throw resultIn.getException();
        }

        return ((ResultLob) resultIn).getCharArray();
    }
