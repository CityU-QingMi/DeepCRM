    public static String getOperationSpec(int type) {

        switch (type) {

            case INSERT_AFTER :
            case INSERT_AFTER_ROW :
            case INSERT_BEFORE_ROW : {
                return "INSERT";
            }
            case UPDATE_AFTER :
            case UPDATE_AFTER_ROW :
            case UPDATE_BEFORE_ROW : {
                return "UPDATE";
            }
            case DELETE_AFTER :
            case DELETE_AFTER_ROW :
            case DELETE_BEFORE_ROW : {
                return "DELETE";
            }
            default : {
                return "";
            }
        }
    }
