    public Object getValue(Session session) {

        switch (opType) {

            case OpTypes.VALUE :
                return valueData;

            case OpTypes.NEGATE :
                return dataType.negate(nodes[LEFT].getValue(session,
                        nodes[LEFT].dataType));
        }

        Object a = nodes[LEFT].getValue(session);
        Object b = nodes[RIGHT].getValue(session);

        switch (opType) {

            case OpTypes.ADD :
                return dataType.add(session, a, b, nodes[RIGHT].dataType);

            case OpTypes.SUBTRACT :
                return dataType.subtract(session, a, b, nodes[RIGHT].dataType);

            case OpTypes.MULTIPLY :
                return dataType.multiply(a, b);

            case OpTypes.DIVIDE :
                return dataType.divide(session, a, b);

            case OpTypes.CONCAT :
                if (!session.database.sqlConcatNulls
                        && nodes[LEFT].dataType.isCharacterType()) {
                    if (a == null && b != null) {
                        a = "";
                    } else if (a != null && b == null) {
                        b = "";
                    }
                }

                return dataType.concat(session, a, b);

            default :
                throw Error.runtimeError(ErrorCode.U_S0500, "Expression");
        }
    }
