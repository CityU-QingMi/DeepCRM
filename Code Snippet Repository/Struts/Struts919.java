    @Override
    public String toString() {
        if( hasStrValue ) {
            return strValue;
        }

        switch (type) {
        case T_CHARS:
            strValue=charC.toString();
            hasStrValue=true;
            return strValue;
        case T_BYTES:
            strValue=byteC.toString();
            hasStrValue=true;
            return strValue;
        }
        return null;
    }
