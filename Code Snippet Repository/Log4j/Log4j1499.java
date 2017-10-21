    static void validatePositionalParameters(List<Field> positionalParametersFields) {
        int min = 0;
        for (Field field : positionalParametersFields) {
            Range index = Range.parameterIndex(field);
            if (index.min > min) {
                throw new ParameterIndexGapException("Missing field annotated with @Parameter(index=" + min +
                        "). Nearest field '" + field.getName() + "' has index=" + index.min);
            }
            min = Math.max(min, index.max);
            min = min == Integer.MAX_VALUE ? min : min + 1;
        }
    }
