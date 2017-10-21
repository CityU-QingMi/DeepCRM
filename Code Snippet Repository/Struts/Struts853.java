    public static String getToken(String tokenName) {
        if (tokenName == null ) {
            return null;
        }
        HttpParameters params = ActionContext.getContext().getParameters();
        Parameter parameter = params.get(tokenName);

        if (!parameter.isDefined()) {
            LOG.warn("Could not find token mapped to token name: {}", tokenName);
            return null;
        }
        return parameter.getValue();
    }
