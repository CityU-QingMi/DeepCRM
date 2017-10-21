    Type readDataType(RowInputBinary in) throws IOException {

        int     typeCode = in.readType();
        boolean isArray  = typeCode == Types.SQL_ARRAY;

        if (isArray) {
            typeCode = in.readType();
        }

        long size  = in.readLong();
        int  scale = in.readInt();
        Type type = Type.getType(typeCode, Type.SQL_VARCHAR.getCharacterSet(),
                                 Type.SQL_VARCHAR.getCollation(), size, scale);

        if (isArray) {
            type = new ArrayType(type, ArrayType.defaultArrayCardinality);
        }

        return type;
    }
