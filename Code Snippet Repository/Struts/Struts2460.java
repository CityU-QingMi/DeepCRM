    public Map<String, ResultTypeConfig> getResultTypesByExtension(PackageConfig packageConfig) {
        Map<String, ResultTypeConfig> results = packageConfig.getAllResultTypeConfigs();

        ResultTypeConfig dispatcher = disableParse(results.get("dispatcher"));
        ResultTypeConfig velocity = disableParse(results.get("velocity"));
        ResultTypeConfig freemarker = disableParse(results.get("freemarker"));

        Map<String, ResultTypeConfig> resultsByExtension = new HashMap<>();
        resultsByExtension.put("jsp", dispatcher);
        resultsByExtension.put("jspf", dispatcher);
        resultsByExtension.put("jspx", dispatcher);
        resultsByExtension.put("vm", velocity);
        resultsByExtension.put("ftl", freemarker);
        resultsByExtension.put("html", dispatcher);
        resultsByExtension.put("htm", dispatcher);
        return resultsByExtension;
    }
