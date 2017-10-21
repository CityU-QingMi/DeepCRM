    static boolean isComparisonOperator(final int type) {
        switch(type) {
            case EQ :
            case LT :
            case GT :
            case LTE :
            case GTE : {
                return true;
            }
            default : {
                return false;
            }
        }
    }
