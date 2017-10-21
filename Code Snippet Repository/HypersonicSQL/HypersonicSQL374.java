        ExpressionColumn[] getParameters() {

            if (parameters.size() == 0) {
                return ExpressionColumn.emptyArray;
            }

            ExpressionColumn[] result =
                new ExpressionColumn[parameters.size()];

            parameters.valuesToArray(result);
            parameters.clear();

            return result;
        }
