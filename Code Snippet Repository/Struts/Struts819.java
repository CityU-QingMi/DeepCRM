    protected Collection<String> conditionalParseCollection(String param, ActionInvocation invocation, boolean excludeEmptyElements) {
        if (parse && param != null && invocation != null) {
            return TextParseUtil.translateVariablesCollection(
                param, 
                invocation.getStack(),
                excludeEmptyElements,
                new EncodingParsedValueEvaluator());
        } else {
            Collection<String> collection = new ArrayList<>(1);
            collection.add(param);
            return collection;
        }
    }
