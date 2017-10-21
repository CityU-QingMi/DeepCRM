    Object getValue(Session session, Type type) {

        switch (opType) {

            case OpTypes.TABLE : {
                materialise(session);

                Object[] value = table.getValues(session);

                if (value.length == 1) {
                    return value[0];
                }

                return value;
            }
            default :
                throw Error.runtimeError(ErrorCode.U_S0500, "Expression");
        }
    }
