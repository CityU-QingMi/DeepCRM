    public void setChars(SessionInterface session, long pos, char[] chars,
                         int offset, int len) {

        if (offset != 0 || len != chars.length) {
            if (!isInLimits(chars.length, offset, len)) {
                throw Error.error(ErrorCode.X_22001);
            }

            if (offset != 0 || len != chars.length) {
                char[] newChars = new char[len];

                System.arraycopy(chars, offset, newChars, 0, len);

                chars = newChars;
            }
        }

        ResultLob resultOut = ResultLob.newLobSetCharsRequest(id, pos, chars);
        Result    resultIn  = session.execute(resultOut);

        if (resultIn.isError()) {
            throw resultIn.getException();
        }

        this.length = ((ResultLob) resultIn).getBlockLength();
    }
