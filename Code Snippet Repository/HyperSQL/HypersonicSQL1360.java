    public static int getCode(String sqlState) {

        try {
            Field[] fields = ErrorCode.class.getDeclaredFields();

            for (int i = 0; i < fields.length; i++) {
                String name = fields[i].getName();

                if (name.length() == 7 && name.endsWith(sqlState)) {
                    return fields[i].getInt(ErrorCode.class);
                }
            }
        } catch (IllegalAccessException e) {}

        return -1;
    }
