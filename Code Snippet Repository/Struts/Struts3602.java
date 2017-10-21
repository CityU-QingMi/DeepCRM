    protected void processResult() throws Exception {
        String timerKey = "processResult: " + getResultCode();
        try {
            UtilTimerStack.push(timerKey);

            HttpServletRequest request = ServletActionContext.getRequest();
            HttpServletResponse response = ServletActionContext.getResponse();

            // Select the target
            selectTarget();

            // Get the httpHeaders
            if (httpHeaders == null) {
                httpHeaders = new DefaultHttpHeaders(resultCode);
            }

            // Apply headers
            if (!hasErrors) {
                httpHeaders.apply(request, response, target);
            } else {
                disableCatching(response);
            }

            // Don't return content on a not modified
            if (httpHeaders.getStatus() != HttpServletResponse.SC_NOT_MODIFIED ) {
                executeResult();
            } else {
                LOG.debug("Result not processed because the status code is not modified.");
            }

        } finally {
            UtilTimerStack.pop(timerKey);
        }
    }
