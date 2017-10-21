    @Override
    public Object evaluate(String expression, Request request) {
        Object result = expression;

        HttpServletRequest httpRequest = ServletUtil.getServletRequest(request).getRequest();
        ActionContext ctx = ServletActionContext.getActionContext(httpRequest);

        if (ctx == null) {
            LOG.error("Cannot obtain HttpServletRequest from [{}]", request.getClass().getName());
            throw new ConfigurationException("There is no ActionContext for current request!");
        }

        TextProviderFactory tpf = ctx.getContainer().getInstance(TextProviderFactory.class);
        TextProvider textProvider = tpf.createInstance(ctx.getActionInvocation().getAction().getClass());

        if (textProvider != null) {
            LOG.debug("Trying find text [{}] using TextProvider {}", expression, textProvider);
            result = textProvider.getText(expression);
        }
        return result;
    }
