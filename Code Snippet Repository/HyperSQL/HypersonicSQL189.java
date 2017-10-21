    void swapCondition() {

        int i = OpTypes.EQUAL;

        switch (opType) {

            case OpTypes.GREATER_EQUAL :
            case OpTypes.GREATER_EQUAL_PRE :
                i = OpTypes.SMALLER_EQUAL;
                break;

            case OpTypes.SMALLER_EQUAL :
                i = OpTypes.GREATER_EQUAL;
                break;

            case OpTypes.SMALLER :
                i = OpTypes.GREATER;
                break;

            case OpTypes.GREATER :
                i = OpTypes.SMALLER;
                break;

            case OpTypes.NOT_DISTINCT :
                i = OpTypes.NOT_DISTINCT;
                break;

            case OpTypes.EQUAL :
                break;

            default :
                throw Error.runtimeError(ErrorCode.U_S0500,
                                         "ExpressionLogical");
        }

        opType = i;

        Expression e = nodes[LEFT];

        nodes[LEFT]  = nodes[RIGHT];
        nodes[RIGHT] = e;
    }
