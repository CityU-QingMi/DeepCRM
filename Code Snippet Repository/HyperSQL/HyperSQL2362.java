    public String valueString(Object datum) {
        String dataString = hType.convertToString(datum);
        switch (hType.typeCode) {
            case Types.SQL_BOOLEAN :
                return String.valueOf(((Boolean) datum).booleanValue()
                    ? 't' : 'f');
                // Default would probably work fine, since the Driver looks at
                // only the first byte, but this why send an extra 3 or 4 bytes
                // with every data, plus there could be some dependency upon
                // single-character in the driver code somewhere.
            case Types.SQL_VARBINARY :
            case Types.SQL_BINARY :
                dataString = OdbcUtil.hexCharsToOctalOctets(dataString);
                break;
        }
        return dataString;
    }
