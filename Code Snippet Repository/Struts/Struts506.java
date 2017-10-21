    private ValidatorConfig processUrlValidatorAnnotation(UrlValidator v, String fieldName, String methodName) {
        String validatorType = "url";

        Map<String, Object> params = new HashMap<>();

        if (fieldName != null) {
            params.put("fieldName", fieldName);
        } else if (StringUtils.isNotEmpty(v.fieldName())) {
            params.put("fieldName", v.fieldName());
        }
        if (StringUtils.isNotEmpty(v.urlRegex())) {
            params.put("urlRegex", v.urlRegex());
        }
        if (StringUtils.isNotEmpty(v.urlRegexExpression())) {
            params.put("urlRegexExpression", v.urlRegexExpression());
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
