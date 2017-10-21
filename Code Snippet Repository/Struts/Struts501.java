    private ValidatorConfig processCustomValidatorAnnotation(CustomValidator v, String fieldName, String methodName) {

        Map<String, Object> params = new HashMap<>();

        if (fieldName != null) {
            params.put("fieldName", fieldName);
        } else if (StringUtils.isNotEmpty(v.fieldName())) {
            params.put("fieldName", v.fieldName());
        }


        String validatorType = v.type();
        validatorFactory.lookupRegisteredValidatorType(validatorType);

        Annotation[] recursedAnnotations = v.parameters();

        if (recursedAnnotations != null) {
            for (Annotation a2 : recursedAnnotations) {
                if (a2 instanceof ValidationParameter) {
                    ValidationParameter parameter = (ValidationParameter) a2;
                    String parameterName = parameter.name();
                    String parameterValue = parameter.value();
                    params.put(parameterName, parameterValue);
                }
            }
        }

        return new ValidatorConfig.Builder(validatorType)
                .addParams(params)
                .addParam("methodName", methodName)
                .shortCircuit(v.shortCircuit())
                .defaultMessage(v.message())
                .messageKey(v.key())
                .messageParams(v.messageParams())
                .build();
    }
