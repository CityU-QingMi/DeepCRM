    protected ActionContext readActionContext(Request request) {
        LOG.debug("Obtaining HttpServletRequest based on [{}]", request.getClass().getName());

        HttpServletRequest httpRequest = ServletUtil.getServletRequest(request).getRequest();
        ActionContext ctx = ServletActionContext.getActionContext(httpRequest);

        if (ctx == null) {
            LOG.error("Cannot obtain HttpServletRequest from [{}]", request.getClass().getName());
            throw new ConfigurationException("There is no ActionContext for current request!");
        }

        return ctx;
    }
