    private void findResult() throws Exception {

        boolean isHttpHeaderResult = false;
        if (result != null && result instanceof HttpHeaderResult) {
            result = null;
            isHttpHeaderResult = true;
        }

        if (result == null && resultCode != null && !Action.NONE.equals(resultCode)
                && unknownHandlerManager.hasUnknownHandlers()) {

            // Find result by resultCode
            this.result = unknownHandlerManager.handleUnknownResult(
                    invocationContext, proxy.getActionName(), proxy.getConfig(), resultCode);
        }

        if (result == null && hasErrors && defaultErrorResultName != null) {

            // Get default error result
            ResultConfig resultConfig = this.proxy.getConfig().getResults().get(defaultErrorResultName);
            if (resultConfig != null) {
                this.result = objectFactory.buildResult(resultConfig, invocationContext.getContextMap());
                LOG.debug("Found default error result.");
            }
        }

        if (result == null && resultCode != null &&
                !Action.NONE.equals(resultCode) && !isHttpHeaderResult) {
            throw new ConfigurationException("No result defined for action "
                    + getAction().getClass().getName()
                    + " and result " + getResultCode(), proxy.getConfig());
        }
    }
