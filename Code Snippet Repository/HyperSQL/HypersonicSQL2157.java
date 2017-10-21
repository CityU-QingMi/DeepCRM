    public static String tiToString(int ti) {
        switch (ti) {
            case Connection.TRANSACTION_READ_UNCOMMITTED:
                return "TRANSACTION_READ_UNCOMMITTED";
            case Connection.TRANSACTION_READ_COMMITTED:
                return "TRANSACTION_READ_COMMITTED";
            case Connection.TRANSACTION_REPEATABLE_READ:
                return "TRANSACTION_REPEATABLE_READ";
            case Connection.TRANSACTION_SERIALIZABLE:
                return "TRANSACTION_SERIALIZABLE";
            case Connection.TRANSACTION_NONE:
                return "TRANSACTION_NONE";
        }
        return "Custom Transaction Isolation numerical value: " + ti;
    }
