    public Context createContext(ValueStack stack, HttpServletRequest req, HttpServletResponse res) {
        Context result = null;
        VelocityContext[] chainedContexts = prepareChainedContexts(req, res, stack.getContext());
        StrutsVelocityContext context = new StrutsVelocityContext(chainedContexts, stack);
        Map standardMap = ContextUtil.getStandardContext(stack, req, res);
        for (Iterator iterator = standardMap.entrySet().iterator(); iterator.hasNext();) {
            Map.Entry entry = (Map.Entry) iterator.next();
            context.put((String) entry.getKey(), entry.getValue());
        }
        context.put(STRUTS, new VelocityStrutsUtil(velocityEngine, context, stack, req, res));


        ServletContext ctx = null;
        try {
            ctx = ServletActionContext.getServletContext();
        } catch (NullPointerException npe) {
            // in case this was used outside the lifecycle of struts servlet
            LOG.debug("internal toolbox context ignored");
        }

        if (toolboxManager != null && ctx != null) {
            ChainedContext chained = new ChainedContext(context, velocityEngine, req, res, ctx);
            chained.setToolbox(toolboxManager.getToolbox(chained));
            result = chained;
        } else {
            result = context;
        }

        req.setAttribute(KEY_VELOCITY_STRUTS_CONTEXT, result);
        return result;
    }
