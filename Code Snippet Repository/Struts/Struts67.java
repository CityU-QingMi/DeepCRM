    private boolean isInChainHistory(String namespace, String actionName, String methodName) {
        LinkedList<? extends String> chainHistory = ActionChainResult.getChainHistory();

        if (chainHistory == null) {
            return false;
        } else {
            //  Actions to skip
            Set<String> skipActionsList = new HashSet<>();
            if (skipActions != null && skipActions.length() > 0) {
                ValueStack stack = ActionContext.getContext().getValueStack();
                String finalSkipActions = TextParseUtil.translateVariables(this.skipActions, stack);
                skipActionsList.addAll(TextParseUtil.commaDelimitedStringToSet(finalSkipActions));
            }
            if (!skipActionsList.contains(actionName)) {
                //  Get if key is in the chain history
                return chainHistory.contains(makeKey(namespace, actionName, methodName));
            }

            return false;
        }
    }
