    private void validate(Validator validator) throws Exception {
        if (validator.getValidatorType().equals("regex")) {
            validateRegexValidator((RegexFieldValidator) validator);
        } else if (validator.getValidatorType().equals("conditionalvisitor")) {
            validateConditionalFieldVisitorValidator((ConditionalVisitorFieldValidator) validator);
        } else if (validator.getValidatorType().equals("conversion")) {
            validateConversionFieldErrorVisitorValidator((ConversionErrorFieldValidator) validator);
        } else if (validator.getValidatorType().equals("myValidator")) {
            validateMyValidator((MyValidator) validator);
        } else if (validator.getValidatorType().equals("date")) {
            validateDateRangeFieldValidator((DateRangeFieldValidator) validator);
        } else if (validator.getValidatorType().equals("double")) {
            validateDoubleRangeFieldValidator((DoubleRangeFieldValidator) validator);
        } else if (validator.getValidatorType().equals("email")) {
            validateEmailValidator((EmailValidator) validator);
        } else if (validator.getValidatorType().equals("creditcard")) {
            validateCreditCardValidator((CreditCardValidator) validator);
        } else if (validator.getValidatorType().equals("expression")) {
            validateExpressionValidator((ExpressionValidator) validator);
        } else if (validator.getValidatorType().equals("fieldexpression")) {
            validateFieldExpressionValidator((FieldExpressionValidator) validator);
        } else if (validator.getValidatorType().equals("int")) {
            validateIntRangeFieldValidator((IntRangeFieldValidator) validator);
        } else if (validator.getValidatorType().equals("long")) {
            validateLongRangeFieldValidator((LongRangeFieldValidator) validator);
        } else if (validator.getValidatorType().equals("required")) {
            validateRequiredFieldValidator((RequiredFieldValidator) validator);
        } else if (validator.getValidatorType().equals("requiredstring")) {
            validateRequiredStringValidator((RequiredStringValidator) validator);
        } else if (validator.getValidatorType().equals("short")) {
            validateShortRangeFieldValidator((ShortRangeFieldValidator) validator);
        } else if (validator.getValidatorType().equals("stringlength")) {
            validateStringLengthFieldValidator((StringLengthFieldValidator) validator);
        } else if (validator.getValidatorType().equals("url")) {
            validateUrlValidator((URLValidator) validator);
        } else if (validator.getValidatorType().equals("visitor")) {
            validateVisitorFieldValidator((VisitorFieldValidator) validator);
        }
    }
