    public static String getForEachSpec(int type) {

        switch (type) {

            case INSERT_BEFORE_ROW :
            case INSERT_AFTER_ROW :
            case UPDATE_BEFORE_ROW :
            case UPDATE_AFTER_ROW :
            case DELETE_AFTER_ROW :
            case DELETE_BEFORE_ROW : {
                return "FOR EACH ROW";
            }
            default : {
                return "FOR EACH STATEMENT";
            }
        }
    }
