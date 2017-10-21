    private void executeResult() throws Exception {

        // Get handler by representation
        ContentTypeHandler handler = handlerSelector.getHandlerForResponse(
                ServletActionContext.getRequest(), ServletActionContext.getResponse());

        // get the result
        this.result = createResult();

        if (this.result instanceof HttpHeaderResult) {

            // execute the result to apply headers and status in every representations
            this.result.execute(this);
            updateStatusFromResult();
        }

        if (handler != null && !(handler instanceof HtmlHandler)) {

            // Specific representation (json, xml...)
            resultCode = handlerSelector.handleResult(this, httpHeaders, target);
        } else {
            // Normal struts execution (html o other struts result)
            findResult();
            if (result != null) {
                this.result.execute(this);
            } else {
                if (LOG.isDebugEnabled()) {
                    LOG.debug("No result returned for action {} at {}", getAction().getClass().getName(), proxy.getConfig().getLocation());
                }
            }
        }
    }
