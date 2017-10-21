    protected void writeChar(String s, Type t) {

        switch (t.typeCode) {

            case Types.SQL_CHAR :
                writeString(s);

                return;

            case Types.SQL_VARCHAR :
                writeVarString(s);

                return;

            default :
                writeLongVarString(s);

                return;
        }
    }
