    public void resolveTypes(Session session, Expression parent) {

        for (int i = 0; i < nodes.length; i++) {
            if (nodes[i] != null) {
                nodes[i].resolveTypes(session, this);
            }
        }

        if (nodes[LEFT].dataType == null) {
            throw Error.error(ErrorCode.X_42567);
        }

        if (!nodes[LEFT].dataType.isArrayType()) {
            throw Error.error(ErrorCode.X_42563);
        }

        dataType = nodes[LEFT].dataType.collectionBaseType();

        if (nodes[RIGHT].opType == OpTypes.DYNAMIC_PARAM) {
            nodes[RIGHT].dataType = Type.SQL_INTEGER;
        }
    }
