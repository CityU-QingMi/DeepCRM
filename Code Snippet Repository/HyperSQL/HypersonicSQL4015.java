    static boolean isLogicalOperator(final int type) {
        switch(type) {
            case AND :
            case OR :
            case XOR :
            case NOT : {
                return true;
            }
            default : {
                return false;
            }
        }
    }
