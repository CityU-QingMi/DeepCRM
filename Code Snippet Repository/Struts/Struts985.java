    @Inject(StrutsConstants.STRUTS_VELOCITY_CONTEXTS)
    public void setChainedContexts(String contexts) {
        // we expect contexts to be a comma separated list of classnames
        StringTokenizer st = new StringTokenizer(contexts, ",");
        List<String> contextList = new ArrayList<>();

        while (st.hasMoreTokens()) {
            String classname = st.nextToken();
            contextList.add(classname);
        }
        if (contextList.size() > 0) {
            String[] chainedContexts = new String[contextList.size()];
            contextList.toArray(chainedContexts);
            this.chainedContextNames = chainedContexts;
        }
    }
