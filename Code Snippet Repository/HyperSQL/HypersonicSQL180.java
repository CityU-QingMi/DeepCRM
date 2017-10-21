    void resolveTypesForAllAny(Session session) {

        int degree = nodes[LEFT].getDegree();

        if (degree == 1 && nodes[LEFT].opType != OpTypes.ROW) {
            nodes[LEFT] = new Expression(OpTypes.ROW,
                                         new Expression[]{ nodes[LEFT] });
        }

        if (nodes[RIGHT].opType == OpTypes.VALUELIST) {
            nodes[RIGHT].prepareTable(session, nodes[LEFT], degree);
            nodes[RIGHT].table.prepareTable(session);
        }

        // encounterd in system generated MATCH predicates
        if (nodes[RIGHT].nodeDataTypes == null) {
            nodes[RIGHT].prepareTable(session, nodes[LEFT], degree);
        }

        if (degree != nodes[RIGHT].nodeDataTypes.length) {
            throw Error.error(ErrorCode.X_42564);
        }

        if (nodes[RIGHT].opType == OpTypes.VALUELIST) {}

        if (nodes[LEFT].nodeDataTypes == null) {
            nodes[LEFT].nodeDataTypes = new Type[nodes[LEFT].nodes.length];
        }

        for (int i = 0; i < nodes[LEFT].nodeDataTypes.length; i++) {
            Type type = nodes[LEFT].nodes[i].dataType;

            if (type == null) {
                type = nodes[RIGHT].nodeDataTypes[i];
            }

            if (type == null) {
                throw Error.error(ErrorCode.X_42567);
            }

            if (type.typeComparisonGroup
                    != nodes[RIGHT].nodeDataTypes[i].typeComparisonGroup) {
                throw Error.error(ErrorCode.X_42563);
            }

            nodes[LEFT].nodeDataTypes[i]  = type;
            nodes[LEFT].nodes[i].dataType = type;
        }
    }
