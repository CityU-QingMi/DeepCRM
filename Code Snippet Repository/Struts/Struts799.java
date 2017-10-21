    protected void printContext(PrettyPrintWriter writer) {
        ActionContext ctx = ActionContext.getContext();
        writer.startNode(DEBUG_PARAM);
        serializeIt(ctx.getParameters(), "parameters", writer, new ArrayList<>());
        writer.startNode("context");
        String key;
        Map ctxMap = ctx.getContextMap();
        for (Object o : ctxMap.keySet()) {
            key = o.toString();
            boolean print = !ignoreKeys.contains(key);

            for (String ignorePrefixe : ignorePrefixes) {
                if (key.startsWith(ignorePrefixe)) {
                    print = false;
                    break;
                }
            }
            if (print) {
                serializeIt(ctxMap.get(key), key, writer, new ArrayList<>());
            }
        }
        writer.endNode();
        Map requestMap = (Map) ctx.get("request");
        serializeIt(requestMap, "request", writer, filterValueStack(requestMap));
        serializeIt(ctx.getSession(), "session", writer, new ArrayList<>());

        ValueStack stack = (ValueStack) ctx.get(ActionContext.VALUE_STACK);
        serializeIt(stack.getRoot(), "valueStack", writer, new ArrayList<>());
        writer.endNode();
    }
