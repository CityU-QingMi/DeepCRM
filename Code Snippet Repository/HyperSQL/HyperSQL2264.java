    protected void writeFieldType(Type type) {

        writeBytes(nextSep);

        switch (type.typeCode) {

            case Types.SQL_VARCHAR :
                nextSep    = varSep;
                nextSepEnd = varSepEnd;
                break;

            default :
                nextSep    = fieldSep;
                nextSepEnd = fieldSepEnd;
                break;
        }
    }
