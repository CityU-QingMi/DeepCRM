    private ValidatorConfig processShortRangeFieldValidatorAnnotation(ShortRangeFieldValidator v, String fieldName, String methodName) {
        String validatorType = "short";

        Map<String, Object> params = new HashMap<>();

        if (fieldName != null) {
            params.put("fieldName", fieldName);
        } else if (StringUtils.isNotEmpty(v.fieldName())) {
            params.put("fieldName", v.fieldName());
        }

        if (StringUtils.isNotEmpty(v.min())) {
            params.put("min", v.min());
        }
        if (StringUtils.isNotEmpty(v.max())) {
            params.put("max", v.max());
        }
        if (StringUtils.isNotEmpty(v.maxExpression())) {
            params.put("maxExpression", v.maxExpression());
        }
        if (StringUtils.isNotEmpty(v.minExpression())) {
            params.put("minExpression", v.minExpression());
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
