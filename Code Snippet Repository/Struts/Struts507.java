    private ValidatorConfig processStringLengthFieldValidatorAnnotation(StringLengthFieldValidator v, String fieldName, String methodName) {
        String validatorType = "stringlength";

        Map<String, Object> params = new HashMap<>();

        if (fieldName != null) {
            params.put("fieldName", fieldName);
        } else if (StringUtils.isNotEmpty(v.fieldName())) {
            params.put("fieldName", v.fieldName());
        }

        if (StringUtils.isNotEmpty(v.maxLength())) {
            params.put("maxLength", v.maxLength());
        }
        if (StringUtils.isNotEmpty(v.minLength())) {
            params.put("minLength", v.minLength());
        }
        if (StringUtils.isNotEmpty(v.maxLengthExpression())) {
            params.put("maxLengthExpression", v.maxLengthExpression());
        }
        if (StringUtils.isNotEmpty(v.minLengthExpression())) {
            params.put("minLengthExpression", v.minLengthExpression());
        }
        if (StringUtils.isNotEmpty(v.trimExpression())){
            params.put("trimExpression", v.trimExpression());
        } else {
            params.put("trim", String.valueOf(v.trim()));
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
