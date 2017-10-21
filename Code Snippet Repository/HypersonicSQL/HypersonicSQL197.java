    private void resolveRowTypes() {

        for (int i = 0; i < nodes[LEFT].nodeDataTypes.length; i++) {
            Type leftType  = nodes[LEFT].nodeDataTypes[i];
            Type rightType = nodes[RIGHT].nodeDataTypes[i];

            if (leftType == null) {
                leftType = nodes[LEFT].nodeDataTypes[i] = rightType;
            } else if (nodes[RIGHT].dataType == null) {
                rightType = nodes[RIGHT].nodeDataTypes[i] = leftType;
            }

            if (leftType == null || rightType == null) {
                throw Error.error(ErrorCode.X_42567);
            }

            if (leftType.typeComparisonGroup
                    != rightType.typeComparisonGroup) {
                throw Error.error(ErrorCode.X_42562);
            } else if (leftType.isDateTimeType()) {
                if (leftType.isDateTimeTypeWithZone()
                        ^ rightType.isDateTimeTypeWithZone()) {
                    nodes[LEFT].nodes[i] =
                        new ExpressionOp(nodes[LEFT].nodes[i]);
                    nodes[LEFT].nodeDataTypes[i] =
                        nodes[LEFT].nodes[i].dataType;
                }
            }
        }
    }
