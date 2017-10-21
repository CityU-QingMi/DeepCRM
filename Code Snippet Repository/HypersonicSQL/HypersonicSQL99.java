    public static DatabaseType get(String value) {
        if (DB_MEM.value.equals(value)) {
            return DB_MEM;
        }


        if (DB_FILE.value.equals(value)) {
            return DB_FILE;
        }

        if (DB_RES.value.equals(value)) {
            return DB_RES;
        }

        throw Error.runtimeError(ErrorCode.U_S0500, "DatabaseType");
    }
