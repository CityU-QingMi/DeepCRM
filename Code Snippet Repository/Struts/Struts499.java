    private ValidatorConfig processDateRangeFieldValidatorAnnotation(DateRangeFieldValidator v, String fieldName, String methodName) {
        String validatorType = "date";

        Map<String, Object> params = new HashMap<>();

        if (fieldName != null) {
            params.put("fieldName", fieldName);
        } else if (v.fieldName() != null && v.fieldName().length() > 0) {
            params.put("fieldName", v.fieldName());
        }
        if (v.min() != null && v.min().length() > 0) {
            final Date minDate = parseDateString(v.min(), v.dateFormat());
            params.put("min", minDate == null ? v.min() : minDate);
        }
        if (v.max() != null && v.max().length() > 0) {
            final Date maxDate = parseDateString(v.max(), v.dateFormat());
            params.put("max", maxDate == null ? v.max() : maxDate);
        }

        if (StringUtils.isNotEmpty(v.minExpression())) {
            params.put("minExpression", v.minExpression());
        }
        if (StringUtils.isNotEmpty(v.maxExpression())) {
            params.put("maxExpression", v.maxExpression());
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
