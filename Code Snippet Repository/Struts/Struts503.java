    private ValidatorConfig processRegexFieldValidatorAnnotation(RegexFieldValidator v, String fieldName, String methodName) {
        String validatorType = "regex";

        Map<String, Object> params = new HashMap<>();

        if (fieldName != null) {
            params.put("fieldName", fieldName);
        } else if (StringUtils.isNotEmpty(v.fieldName())) {
            params.put("fieldName", v.fieldName());
        }

        params.put("regex", v.regex());
        params.put("regexExpression", v.regexExpression());

        validatorFactory.lookupRegisteredValidatorType(validatorType);
        return new ValidatorConfig.Builder(validatorType)
                .addParams(params)
                .addParam("methodName", methodName)
                .addParam("trim", v.trim())
                .addParam("trimExpression", v.trimExpression())
                .addParam("caseSensitive", v.caseSensitive())
                .addParam("caseSensitiveExpression", v.caseSensitiveExpression())
                .shortCircuit(v.shortCircuit())
                .defaultMessage(v.message())
                .messageKey(v.key())
                .messageParams(v.messageParams())
                .build();
    }
