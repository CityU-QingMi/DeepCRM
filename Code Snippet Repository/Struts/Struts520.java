    @Override
    public void doValidate(Object object) throws ValidationException {
        String fieldName = getFieldName();
        String fullFieldName = getValidatorContext().getFullFieldName(fieldName);
        ActionContext context = ActionContext.getContext();
        Map<String, Object> conversionErrors = context.getConversionErrors();
        
        if (conversionErrors.containsKey(fullFieldName)) {
            if (StringUtils.isBlank(defaultMessage)) {
                defaultMessage = XWorkConverter.getConversionErrorMessage(fullFieldName, context.getValueStack());
            }
            
            addFieldError(fieldName, object);
        }
    }
