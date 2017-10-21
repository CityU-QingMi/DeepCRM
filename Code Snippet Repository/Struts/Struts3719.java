    @Override
    public Object evaluate(String expression, Request request) {
        try {
            HttpServletRequest httpRequest = ServletUtil.getServletRequest(request).getRequest();
            ActionContext ctx = ServletActionContext.getActionContext(httpRequest);

            if (ctx == null) {
                LOG.error("Cannot obtain HttpServletRequest from [{}]", request.getClass().getName());
                throw new ConfigurationException("There is no ActionContext for current request!");
            }

            OgnlUtil ognlUtil = ctx.getContainer().getInstance(OgnlUtil.class);

            LOG.debug("Trying evaluate expression [{}] using OgnlUtil's getValue", expression);
            Object result = ognlUtil.getValue(expression, ctx.getContextMap(), ctx.getValueStack().getRoot());

            LOG.debug("Final result of evaluating expression [{}] is: {}", expression, result);

            return result;
        } catch (OgnlException e) {
            throw new EvaluationException(e);
        }
    }
