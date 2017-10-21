    public void execute(ActionInvocation actionInvocation) {
        GxpResourceProvider provider = getProvider();
        try {
            getGxpClosure().write(provider.getWriter(), new GxpContext(provider.getLocale(), outputXml));
        } catch (Exception e) {
            throw new RuntimeException("Exception while rendering "
                    + getGxpName()
                    + " coming from "
                    + actionInvocation.getAction().getClass().getName() + ".",
                    e);
        }
    }
