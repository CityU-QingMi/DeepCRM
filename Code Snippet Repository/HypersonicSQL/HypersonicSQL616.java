        private void reverseIndexCondition() {

            if (indexedColumnCount == 0) {
                reversed = true;

                return;
            }

            if (opType == OpTypes.EQUAL || opType == OpTypes.IS_NULL) {

                //
            } else {
                indexEndCondition = null;

                Expression[] temp = indexCond;

                indexCond    = indexEndCond;
                indexEndCond = temp;

                int[] temptypes = opTypes;

                opTypes    = opTypesEnd;
                opTypesEnd = temptypes;

                for (int i = 0; i < indexedColumnCount; i++) {
                    Expression e = indexEndCond[i];

                    indexEndCondition =
                        ExpressionLogical.andExpressions(indexEndCondition, e);
                }

                if (indexedColumnCount > 1
                        && opTypes[indexedColumnCount - 1] == OpTypes.MAX) {
                    indexedColumnCount--;

                    opTypes[indexedColumnCount]    = 0;
                    opTypesEnd[indexedColumnCount] = 0;
                }

                opType    = opTypes[indexedColumnCount - 1];
                opTypeEnd = opTypesEnd[indexedColumnCount - 1];
            }

            reversed = true;
        }
