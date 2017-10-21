    public long getLong() {
        if( hasLongValue ) {
            return longValue;
        }

        switch (type) {
        case T_BYTES:
            longValue=byteC.getLong();
            break;
        default:
            longValue= Long.parseLong(toString());
        }

        hasLongValue=true;
        return longValue;

     }
