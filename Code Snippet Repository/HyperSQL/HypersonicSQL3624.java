    public String getSubString(SessionInterface session, long pos,
                               int length) {

        long clobLength = length(session);

        if (pos >= clobLength) {
            return "";
        }

        if (pos + length >= clobLength) {
            length = (int) (clobLength - pos);
        }


        char[] chars = getChars(session, pos, length);

        return new String(chars);
    }
