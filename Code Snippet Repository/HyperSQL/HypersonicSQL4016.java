    static boolean isValue(final int type) {
        switch (type) {
            case IDENT :
            case STRING :
            case NUMBER : {
                return true;
            }
            default : {
                return false;
            }
        }
    }
