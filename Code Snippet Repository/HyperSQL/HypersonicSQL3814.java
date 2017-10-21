    public static Type getParameterSQLType(Class c) {

        String name;
        int    typeCode;

        if (c == null) {
            throw Error.runtimeError(ErrorCode.U_S0500, "Types");
        }

        if (Void.TYPE.equals(c)) {
            return Type.SQL_ALL_TYPES;
        }

        name     = c.getName();
        typeCode = javaTypeNumbers.get(name, Integer.MIN_VALUE);

        if (typeCode != Integer.MIN_VALUE) {
            return Type.getDefaultTypeWithSize(typeCode);
        }

        if (c.isArray()) {
            Class c1 = c.getComponentType();

            name     = c1.getName();
            typeCode = javaTypeNumbers.get(name, Integer.MIN_VALUE);

            if (typeCode == Types.SQL_ALL_TYPES) {
                return null;
            }

            if (typeCode != Integer.MIN_VALUE) {
                return Type.getDefaultTypeWithSize(typeCode);
            }

            return null;
        }

        if (name.equals("java.sql.Array")) {
            return Type.getDefaultArrayType(Types.SQL_ALL_TYPES);
        }

        return null;
    }
