    private Bundle getCurrentBundle() {
        ActionContext ctx = ActionContext.getContext();
        String bundleName = (String) ctx.get(CURRENT_BUNDLE_NAME);
        if (bundleName == null) {
            ActionInvocation inv = ctx.getActionInvocation();
            ActionProxy proxy = inv.getProxy();
            ActionConfig actionConfig = proxy.getConfig();
            bundleName = packageToBundle.get(actionConfig.getPackageName());
        }
        if (bundleName != null) {
            return osgiHost.getActiveBundles().get(bundleName);
        }
        return null;
    }
