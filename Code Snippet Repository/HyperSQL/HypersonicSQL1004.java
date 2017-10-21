    static Result performAssignment(Session session, int[] variableIndexes,
                                    Expression[] targets, Object[] values,
                                    Type[] sourceTypes) {

        for (int j = 0; j < values.length; j++) {
            Object[] data = ValuePool.emptyObjectArray;

            switch (targets[j].getColumn().getType()) {

                case SchemaObject.PARAMETER :
                    data = session.sessionContext.routineArguments;
                    break;

                case SchemaObject.VARIABLE :
                    data = session.sessionContext.routineVariables;
                    break;

                case SchemaObject.COLUMN :
                    data = session.sessionContext
                        .triggerArguments[TriggerDef.NEW_ROW];
                    break;
            }

            int    colIndex = variableIndexes[j];
            Object value    = values[j];
            Type   targetType;

            if (targets[j].getType() == OpTypes.ARRAY_ACCESS) {
                targetType =
                    targets[j].getLeftNode().getColumn().getDataType()
                        .collectionBaseType();
                value = targetType.convertToType(session, value,
                                                 sourceTypes[j]);
                data[colIndex] =
                    ((ExpressionAccessor) targets[j]).getUpdatedArray(session,
                        (Object[]) data[colIndex], value, true);
            } else {
                targetType = targets[j].getColumn().getDataType();
                value = targetType.convertToType(session, value,
                                                 sourceTypes[j]);
                data[colIndex] = value;
            }
        }

        return Result.updateZeroResult;
    }
