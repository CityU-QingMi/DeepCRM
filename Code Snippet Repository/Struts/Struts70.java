    public String getFormatted(String key, String expr) {
        Map<String, Object> conversionErrors = ActionContext.getContext().getConversionErrors();
        if (conversionErrors.containsKey(expr)) {
            String[] vals = (String[]) conversionErrors.get(expr);
            return vals[0];
        } else {
            final ValueStack valueStack = ActionContext.getContext().getValueStack();
            final Object val = valueStack.findValue(expr);
            return getText(key, Arrays.asList(val));
        }
    }
