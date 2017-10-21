    private ValidatorConfig processDoubleRangeFieldValidatorAnnotation(DoubleRangeFieldValidator v, String fieldName, String methodName) {
        String validatorType = "double";

        Map<String, Object> params = new HashMap<>();

        if (fieldName != null) {
            params.put("fieldName", fieldName);
        } else if (v.fieldName() != null && v.fieldName().length() > 0) {
            params.put("fieldName", v.fieldName());
        }

        if (v.minInclusive() != null && v.minInclusive().length() > 0) {
            params.put("minInclusive", v.minInclusive());
        }
        if (v.maxInclusive() != null && v.maxInclusive().length() > 0) {
            params.put("maxInclusive", v.maxInclusive());
        }

        if (v.minExclusive() != null && v.minExclusive().length() > 0) {
            params.put("minExclusive", v.minExclusive());
        }
        if (v.maxExclusive() != null && v.maxExclusive().length() > 0) {
            params.put("maxExclusive", v.maxExclusive());
        }

        if (StringUtils.isNotEmpty(v.minInclusiveExpression())) {
            params.put("minInclusiveExpression", v.minInclusiveExpression());
        }
        if (StringUtils.isNotEmpty(v.maxInclusiveExpression())) {
            params.put("maxInclusiveExpression", v.maxInclusiveExpression());
        }

        if (StringUtils.isNotEmpty(v.minExclusiveExpression())) {
            params.put("minExclusiveExpression", v.minExclusiveExpression());
        }
        if (StringUtils.isNotEmpty(v.maxExclusiveExpression())) {
            params.put("maxExclusiveExpression", v.maxExclusiveExpression());
        }

        validatorFactory.lookupRegisteredValidatorType(validatorType);
        return new ValidatorConfig.Builder(validatorType)
                .addParams(params)
                .addParam("methodName", methodName)
                .shortCircuit(v.shortCircuit())
                .defaultMessage(v.message())
                .messageKey(v.key())
                .messageParams(v.messageParams())
                .build();
    }
