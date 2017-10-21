    private Result scanResultsByExtension(String ns, String actionName, String pathPrefix,
                                          String resultCode, ActionContext actionContext) {
        Map<String, ResultTypeConfig> resultsByExtension = conventionsService.getResultTypesByExtension(parentPackage);
        Result result = null;
        for (String ext : resultsByExtension.keySet()) {
            if (LOG.isTraceEnabled()) {
                String fqan = ns + "/" + actionName;
                LOG.trace("Trying to locate the correct result for the FQ action [{}]"
                        + " with an file extension of [{}] in the directory [{}] " + "and a result code of [{}]",
                        fqan, ext, pathPrefix, resultCode);
            }

            String path = string(pathPrefix, actionName, nameSeparator, resultCode, ".", ext);
            result = findResult(path, resultCode, ext, actionContext, resultsByExtension);
            if (result != null) {
                break;
            }

            path = string(pathPrefix, actionName, ".", ext);
            result = findResult(path, resultCode, ext, actionContext, resultsByExtension);
            if (result != null) {
                break;
            }

            // Issue #6 - Scan for result-code as page name
            path = string(pathPrefix, resultCode, ".", ext);
            result = findResult(path, resultCode, ext, actionContext, resultsByExtension);
            if (result != null) {
                break;
            }

        }
        return result;
    }
