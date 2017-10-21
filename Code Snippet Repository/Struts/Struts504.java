    private ValidatorConfig processConditionalVisitorFieldValidatorAnnotation(ConditionalVisitorFieldValidator v, String fieldName, String methodName) {
        String validatorType = "conditionalvisitor";

        Map<String, Object> params = new HashMap<>();

        if (fieldName != null) {
            params.put("fieldName", fieldName);
        } else if (StringUtils.isNotEmpty(v.fieldName())) {
            params.put("fieldName", v.fieldName());
        }

        params.put("expression", v.expression());
        params.put("context", v.context());
        params.put("appendPrefix", String.valueOf(v.appendPrefix()));

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
