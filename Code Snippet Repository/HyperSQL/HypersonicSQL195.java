    private void resolveTypesForLogicalOp() {

        if (nodes[LEFT].isUnresolvedParam()) {
            nodes[LEFT].dataType = Type.SQL_BOOLEAN;
        }

        if (nodes[RIGHT].isUnresolvedParam()) {
            nodes[RIGHT].dataType = Type.SQL_BOOLEAN;
        }

        if (nodes[LEFT].dataType == null || nodes[RIGHT].dataType == null) {
            throw Error.error(ErrorCode.X_42571);
        }

        if (nodes[LEFT].opType == OpTypes.ROW
                || nodes[RIGHT].opType == OpTypes.ROW) {
            throw Error.error(ErrorCode.X_42565);
        }

        if (Type.SQL_BOOLEAN != nodes[LEFT].dataType
                || Type.SQL_BOOLEAN != nodes[RIGHT].dataType) {
            throw Error.error(ErrorCode.X_42568);
        }
    }
