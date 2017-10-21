    public static String getWhenSpec(int type) {

        switch (type) {

            case INSERT_BEFORE_ROW :
            case UPDATE_BEFORE_ROW :
            case DELETE_BEFORE_ROW : {
                return "BEFORE";
            }
            case INSERT_AFTER :
            case INSERT_AFTER_ROW :
            case UPDATE_AFTER :
            case UPDATE_AFTER_ROW :
            case DELETE_AFTER :
            case DELETE_AFTER_ROW : {
                return "AFTER";
            }
            default : {
                return "";
            }
        }
    }
