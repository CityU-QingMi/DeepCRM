    protected void writeLongVarString(String s) {

        s = checkConvertString(s, longvarSep);

        if (s == null) {
            return;
        }

        byte[] bytes = getBytes(s);

        write(bytes, 0, bytes.length);

        nextSep    = longvarSep;
        nextSepEnd = longvarSepEnd;
    }
