    void resolveTypesForAlternative(Session session) {

        if (nodes[LEFT].dataType == null) {
            nodes[LEFT].dataType = nodes[RIGHT].dataType;
        }

        if (nodes[RIGHT].dataType == null) {
            nodes[RIGHT].dataType = nodes[LEFT].dataType;
        }

        if (exprSubType == OpTypes.CAST) {
            if (nodes[RIGHT].dataType == null) {
                nodes[RIGHT].dataType = nodes[LEFT].dataType =
                    Type.SQL_VARCHAR_DEFAULT;;
            }

            dataType = nodes[RIGHT].dataType;

            if (!nodes[RIGHT].dataType.equals(nodes[LEFT].dataType)) {
                if (dataType.isCharacterType()) {
                    dataType = Type.SQL_VARCHAR_DEFAULT;
                }

                nodes[LEFT] = new ExpressionOp(nodes[LEFT], dataType);
            }
        } else {
            dataType = Type.getAggregateType(nodes[LEFT].dataType, dataType);
            dataType = Type.getAggregateType(nodes[RIGHT].dataType, dataType);
        }
    }
