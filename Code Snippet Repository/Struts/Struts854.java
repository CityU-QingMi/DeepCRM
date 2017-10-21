    public static String getTokenName() {
        HttpParameters params = ActionContext.getContext().getParameters();

        if (!params.contains(TOKEN_NAME_FIELD)) {
        	LOG.warn("Could not find token name in params.");
            return null;
        }

        Parameter parameter = params.get(TOKEN_NAME_FIELD);
        if (!parameter.isDefined()) {
        	LOG.warn("Got a null or empty token name.");
            return null;
        }
        return parameter.getValue();
    }
