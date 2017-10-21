    protected VelocityContext[] prepareChainedContexts(HttpServletRequest servletRequest, HttpServletResponse servletResponse, Map extraContext) {
        if (this.chainedContextNames == null) {
            return null;
        }
        List contextList = new ArrayList();
        for (int i = 0; i < chainedContextNames.length; i++) {
            String className = chainedContextNames[i];
            try {
                VelocityContext velocityContext = (VelocityContext) objectFactory.buildBean(className, null);
                contextList.add(velocityContext);
            } catch (Exception e) {
                LOG.warn("Warning. {} caught while attempting to instantiate a chained VelocityContext, {} -- skipping", e.getClass().getName(), className);
            }
        }
        if (contextList.size() > 0) {
            VelocityContext[] extraContexts = new VelocityContext[contextList.size()];
            contextList.toArray(extraContexts);
            return extraContexts;
        } else {
            return null;
        }
    }
