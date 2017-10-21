    protected void evaluateExtraParams() {
        boolean isEmptyList = true;
        Collection<String> actionMessages = (List) findValue("actionMessages");
        if (actionMessages != null) {
            for (String message : actionMessages) {
                if (StringUtils.isNotBlank(message)) {
                    isEmptyList = false;
                    break;
                }
            }
        }

        addParameter("isEmptyList", isEmptyList);
        addParameter("escape", escape);
    }
