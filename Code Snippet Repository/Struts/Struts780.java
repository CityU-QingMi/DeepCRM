    protected boolean isRedirect(ActionInvocation invocation, String resultCode) {
        boolean isRedirect = false;
        try {
            ResultConfig resultConfig = invocation.getProxy().getConfig().getResults().get(resultCode);
            if (resultConfig != null) {
                isRedirect = Redirectable.class.isAssignableFrom(Class.forName(resultConfig.getClassName()));
            }
        } catch (Exception e) {
            LOG.warn("Cannot read result!", e);
        }
        return isRedirect;
    }
