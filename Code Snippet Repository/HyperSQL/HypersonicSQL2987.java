    protected String readChar(Type type) {

        String s = null;

        switch (type.typeCode) {

            case Types.SQL_CHAR :
                s = readString();
                break;

            case Types.SQL_VARCHAR :
                s = readVarString();
                break;

            default :
                s = readLongVarString();
                break;
        }

        if (s == null) {
            return null;
        }

        if (s.length() > this.maxPooledStringLength) {
            return s;
        } else {
            return ValuePool.getString(s);
        }
    }
