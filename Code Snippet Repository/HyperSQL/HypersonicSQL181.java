    private Boolean compareValues(Session session, Object left, Object right) {

        int result = 0;

        if (left == null || right == null) {
            return null;
        }

        result = nodes[LEFT].dataType.compare(session, left, right, opType);

        switch (opType) {

            case OpTypes.EQUAL :
                return result == 0 ? Boolean.TRUE
                                   : Boolean.FALSE;

            case OpTypes.NOT_EQUAL :
                return result != 0 ? Boolean.TRUE
                                   : Boolean.FALSE;

            case OpTypes.GREATER :
                return result > 0 ? Boolean.TRUE
                                  : Boolean.FALSE;

            case OpTypes.GREATER_EQUAL :
            case OpTypes.GREATER_EQUAL_PRE :
                return result >= 0 ? Boolean.TRUE
                                   : Boolean.FALSE;

            case OpTypes.SMALLER_EQUAL :
                return result <= 0 ? Boolean.TRUE
                                   : Boolean.FALSE;

            case OpTypes.SMALLER :
                return result < 0 ? Boolean.TRUE
                                  : Boolean.FALSE;

            default :
                throw Error.runtimeError(ErrorCode.U_S0500,
                                         "ExpressionLogical");
        }
    }
