    public void resolveTypes(Session session, Expression parent) {

        for (int i = 0; i < nodes.length; i++) {
            if (nodes[i] != null) {
                nodes[i].resolveTypes(session, this);
            }
        }

        if (nodes[LEFT].getDegree() > 1) {
            nodes[LEFT].dataType = new RowType(nodes[LEFT].nodeDataTypes);
        }

        if (nodes[LEFT].isUnresolvedParam()) {
            throw Error.error(ErrorCode.X_42567);
        }

        if (isDistinctAggregate) {
            if (nodes[LEFT].dataType.isLobType()) {
                throw Error.error(ErrorCode.X_42534);
            }

            if (nodes[LEFT].dataType.isCharacterType()) {
                arrayType = new ArrayType(nodes[LEFT].dataType,
                                          Integer.MAX_VALUE);
            }
        }

        dataType = SetFunction.getType(session, opType, nodes[LEFT].dataType);

        nodes[RIGHT].resolveTypes(session, null);
    }
