    public boolean equals(MessageBytes mb) {
        switch (type) {
        case T_STR:
            return mb.equals( strValue );
        }

        if( mb.type != T_CHARS &&
            mb.type!= T_BYTES ) {
            // it's a string or int/date string value
            return equals( mb.toString() );
        }

        // mb is either CHARS or BYTES.
        // this is either CHARS or BYTES
        // Deal with the 4 cases ( in fact 3, one is symmetric)

        if( mb.type == T_CHARS && type==T_CHARS ) {
            return charC.equals( mb.charC );
        }
        if( mb.type==T_BYTES && type== T_BYTES ) {
            return byteC.equals( mb.byteC );
        }
        if( mb.type== T_CHARS && type== T_BYTES ) {
            return byteC.equals( mb.charC );
        }
        if( mb.type== T_BYTES && type== T_CHARS ) {
            return mb.byteC.equals( charC );
        }
        // can't happen
        return true;
    }
