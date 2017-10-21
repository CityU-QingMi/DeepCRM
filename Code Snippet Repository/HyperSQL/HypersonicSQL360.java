        public void reset() {

            rangeVarIndex = 1;
            subqueryDepth = 0;

            rangeVariables.clear();
            parameters.clear();
            parameterIndexes.clear();
            usedSequences.clear();
            usedRoutines.clear();

            callProcedure = null;

            usedObjects.clear();

            outerRangeGroups = RangeGroup.emptyArray;

            //
            currentDomain               = null;
            contextuallyTypedExpression = false;
        }
