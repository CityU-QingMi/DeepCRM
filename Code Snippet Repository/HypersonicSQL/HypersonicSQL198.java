    void checkRowComparison() {

        if (opType == OpTypes.EQUAL || opType == OpTypes.NOT_EQUAL) {
            return;
        }

        for (int i = 0; i < nodes[LEFT].nodeDataTypes.length; i++) {
            Type leftType  = nodes[LEFT].nodeDataTypes[i];
            Type rightType = nodes[RIGHT].nodeDataTypes[i];

            if (leftType.isArrayType() || leftType.isLobType()
                    || rightType.isLobType()) {
                throw Error.error(ErrorCode.X_42534);
            }
        }
    }
