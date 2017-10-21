    protected String conditionalParse(String param, ActionInvocation invocation) {
        if (parse && param != null && invocation != null) {
            return TextParseUtil.translateVariables(
                param, 
                invocation.getStack(),
                new EncodingParsedValueEvaluator());
        } else {
            return param;
        }
    }
