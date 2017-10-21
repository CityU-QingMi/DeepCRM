    Type readDataTypeSimple(RowInputBinary in) throws IOException {

        int     typeCode = in.readType();
        boolean isArray  = typeCode == Types.SQL_ARRAY;

        if (isArray) {
            typeCode = in.readType();

            return Type.getDefaultArrayType(typeCode);
        }

        return Type.getDefaultType(typeCode);
    }
