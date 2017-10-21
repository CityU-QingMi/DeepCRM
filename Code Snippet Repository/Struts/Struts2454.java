    public Result handleUnknownResult(ActionContext actionContext, String actionName,
                                      ActionConfig actionConfig, String resultCode) throws XWorkException {

        PackageConfig pkg = configuration.getPackageConfig(actionConfig.getPackageName());
        String ns = pkg.getNamespace();
        String pathPrefix = determinePath(actionConfig, ns);

        Result result = scanResultsByExtension(ns, actionName, pathPrefix, resultCode, actionContext);

        if (result == null) {
            // Try /idx/action/index.jsp
            Map<String, ResultTypeConfig> resultsByExtension = conventionsService.getResultTypesByExtension(pkg);
            for (String ext : resultsByExtension.keySet()) {
                if (LOG.isTraceEnabled()) {
                    String fqan = ns + "/" + actionName;
                    LOG.trace("Checking for [{}/index.{}]", fqan, ext);
                }

                String path = string(pathPrefix, actionName, "/index", nameSeparator, resultCode, ".", ext);
                result = findResult(path, resultCode, ext, actionContext, resultsByExtension);
                if (result != null) {
                    break;
                }

                path = string(pathPrefix, actionName, "/index.", ext);
                result = findResult(path, resultCode, ext, actionContext, resultsByExtension);
                if (result != null) {
                    break;
                }
            }
        }

        if (result == null && resultCode != null) {
            //try to find an action to chain to. If the source action is "foo" and
            //the result is "bar", we will try to find an action called "foo-bar"
            //in the same package
            String chainedTo = actionName + nameSeparator + resultCode;
            ActionConfig chainedToConfig = pkg.getActionConfigs().get(chainedTo);
            if (chainedToConfig != null) {
                LOG.trace("Action [{}] used as chain result for [{}] and result [{}]", chainedTo, actionName, resultCode);
                ResultTypeConfig chainResultType = pkg.getAllResultTypeConfigs().get("chain");
                result = buildResult(chainedTo, resultCode, chainResultType, actionContext);
            }
        }

        return result;
    }
