    private ValidatorConfig processRequiredStringValidatorAnnotation(RequiredStringValidator v, String fieldName, String methodName) {
        String validatorType = "requiredstring";

        Map<String, Object> params = new HashMap<>();

        if (fieldName != null) {
            params.put("fieldName", fieldName);
        } else if (StringUtils.isNotEmpty(v.fieldName())) {
            params.put("fieldName", v.fieldName());
        }

        params.put("trim", String.valueOf(v.trim()));

        validatorFactory.lookupRegisteredValidatorType(validatorType);
        return new ValidatorConfig.Builder(validatorType)
                .addParams(params)
                .addParam("methodName", methodName)
                .shortCircuit(v.shortCircuit())
                .defaultMessage(v.message())
                .messageParams(v.messageParams())
                .messageKey(v.key())
                .build();
    }
