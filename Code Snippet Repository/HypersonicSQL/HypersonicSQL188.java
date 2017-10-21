    boolean convertToSmaller() {

        switch (opType) {

            case OpTypes.GREATER_EQUAL :
            case OpTypes.GREATER :
                swapCondition();

                return true;

            case OpTypes.SMALLER_EQUAL :
            case OpTypes.SMALLER :
                return true;

            default :
                return false;
        }
    }
