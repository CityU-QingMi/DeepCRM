    public static Collection<String> translateVariablesCollection(
            char[] openChars, String expression, final ValueStack stack, boolean excludeEmptyElements,
            final ParsedValueEvaluator evaluator, int maxLoopCount) {

        ParsedValueEvaluator ognlEval = new ParsedValueEvaluator() {
            public Object evaluate(String parsedValue) {
                return stack.findValue(parsedValue); // no asType !!!
            }
        };

        Map<String, Object> context = stack.getContext();
        TextParser parser = ((Container)context.get(ActionContext.CONTAINER)).getInstance(TextParser.class);

        Object result = parser.evaluate(openChars, expression, ognlEval, maxLoopCount);

        Collection<String> resultCol;
        if (result instanceof Collection) {
            @SuppressWarnings("unchecked")
            Collection<Object> casted = (Collection<Object>)result;
            resultCol = new ArrayList<>();

            XWorkConverter conv = ((Container)context.get(ActionContext.CONTAINER)).getInstance(XWorkConverter.class);

            for (Object element : casted) {
                String stringElement = (String)conv.convertValue(context, element, String.class);
                if (shallBeIncluded(stringElement, excludeEmptyElements)) {
                    if (evaluator != null) {
                        stringElement = evaluator.evaluate(stringElement).toString();
                    }
                    resultCol.add(stringElement);
                }
            }
        } else {
            resultCol = new ArrayList<>();
            String resultStr = translateVariables(expression, stack, evaluator);
            if (shallBeIncluded(resultStr, excludeEmptyElements)) {
                resultCol.add(resultStr);
            }
        }

        return resultCol;
    }
