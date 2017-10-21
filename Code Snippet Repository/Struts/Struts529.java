    public void repopulateField(Object object) throws ValidationException {

        ActionInvocation invocation = ActionContext.getContext().getActionInvocation();
        Map<String, Object> conversionErrors = ActionContext.getContext().getConversionErrors();

        String fieldName = getFieldName();
        String fullFieldName = getValidatorContext().getFullFieldName(fieldName);
        if (conversionErrors.containsKey(fullFieldName)) {
            Object value = conversionErrors.get(fullFieldName);

            final Map<Object, Object> fakeParams = new LinkedHashMap<Object, Object>();
            boolean doExprOverride = false;

            if (value instanceof String[]) {
                // take the first element, if possible
                String[] tmpValue = (String[]) value;
                if ((tmpValue.length > 0)) {
                    doExprOverride = true;
                    fakeParams.put(fullFieldName, escape(tmpValue[0]));
                } else {
                    LOG.warn("value is an empty array of String or with first element in it as null [{}], will not repopulate conversion error", value);
                }
            } else if (value instanceof String) {
                String tmpValue = (String) value;
                doExprOverride = true;
                fakeParams.put(fullFieldName, escape(tmpValue));
            } else {
                // opps... it should be 
                LOG.warn("conversion error value is not a String or array of String but instead is [{}], will not repopulate conversion error", value);
            }

            if (doExprOverride) {
                invocation.addPreResultListener(new PreResultListener() {
                    public void beforeResult(ActionInvocation invocation, String resultCode) {
                        ValueStack stack = ActionContext.getContext().getValueStack();
                        stack.setExprOverrides(fakeParams);
                    }
                });
            }
        }
    }
