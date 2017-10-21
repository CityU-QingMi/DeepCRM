    private DateRangeFieldValidator prepareValidator(ValidationAction action, ValidatorContext context) {
        ValueStack valueStack = container.getInstance(ValueStackFactory.class).createValueStack();
        valueStack.push(action);

        DateRangeFieldValidator validator = new DateRangeFieldValidator();
        validator.setValueStack(valueStack);

        validator.setMaxExpression("${dateMaxValue}");
        validator.setMinExpression("${dateMinValue}");
        validator.setValidatorContext(context);
        validator.setFieldName("dateRange");
        validator.setDefaultMessage("Max is ${dateMaxValue}, min is ${dateMinValue} but value is ${dateRange}");

        return validator;
    }
