    private boolean convertDateTime(Session session) {

        int a = LEFT;
        int b = RIGHT;

        if (nodes[a].dataType.isDateTimeType()) {

            //
        } else if (nodes[b].dataType.isDateTimeType()) {
            a = RIGHT;
            b = LEFT;
        } else {
            return false;
        }

        if (nodes[a].dataType.isDateTimeTypeWithZone()) {
            return false;
        }

        if (nodes[b].dataType.isCharacterType()) {
            if (nodes[b].opType == OpTypes.VALUE) {
                try {
                    nodes[b].valueData = nodes[a].dataType.castToType(session,
                            nodes[b].valueData, nodes[b].dataType);
                    nodes[b].dataType = nodes[a].dataType;
                } catch (HsqlException e) {
                    if (nodes[a].dataType == Type.SQL_DATE) {
                        nodes[b].valueData =
                            Type.SQL_TIMESTAMP.castToType(session,
                                                          nodes[b].valueData,
                                                          nodes[b].dataType);
                        nodes[b].dataType = Type.SQL_TIMESTAMP;
                    }
                }

                return true;
            } else {
                nodes[b] = new ExpressionOp(nodes[b], nodes[a].dataType);

                nodes[b].resolveTypes(session, this);

                return true;
            }
        }

        return false;
    }
