    public void resolveTypes(Session session, Expression parent) {

        nodes[LEFT].resolveTypes(session, parent);

        if (nodes[LEFT].isUnresolvedParam()) {
            throw Error.error(ErrorCode.X_42567);
        }

        dataType = nodes[LEFT].dataType;

        if (collation != null && !dataType.isCharacterType()) {
            throw Error.error(ErrorCode.X_2H000,
                              collation.getName().statementName);
        }
    }
