    protected void addAutoStartBundles(Properties configProps) {
        //starts system bundles in level 1
        List<String> bundleJarsLevel1 = new ArrayList<String>();
        bundleJarsLevel1.add(getJarUrl(ShellService.class));
        bundleJarsLevel1.add(getJarUrl(ServiceTracker.class));

        configProps.put(AutoProcessor.AUTO_START_PROP + ".1", StringUtils.join(bundleJarsLevel1, " "));

        //get a list of directories under /bundles with numeric names (the runlevel)
        Map<String, String> runLevels = getRunLevelDirs("bundles");
        if (runLevels.isEmpty()) {
            //there are no run level dirs, search for bundles in that dir
            List<String> bundles = getBundlesInDir("bundles");
            if (!bundles.isEmpty()) {
                configProps.put(AutoProcessor.AUTO_START_PROP + ".2", StringUtils.join(bundles, " "));
            }
        } else {
            for (String runLevel : runLevels.keySet()) {
                if ("1".endsWith(runLevel)) {
                    throw new StrutsException("Run level dirs must be greater than 1. Run level 1 is reserved for the Felix bundles");
                }
                List<String> bundles = getBundlesInDir(runLevels.get(runLevel));
                configProps.put(AutoProcessor.AUTO_START_PROP + "." + runLevel, StringUtils.join(bundles, " "));
            }
        }
    }
