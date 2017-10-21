    @PluginFactory
    public static DeleteAction createDeleteAction(
            // @formatter:off
            @PluginAttribute("basePath") final String basePath, 
            @PluginAttribute(value = "followLinks") final boolean followLinks,
            @PluginAttribute(value = "maxDepth", defaultInt = 1) final int maxDepth,
            @PluginAttribute(value = "testMode") final boolean testMode,
            @PluginElement("PathSorter") final PathSorter sorterParameter,
            @PluginElement("PathConditions") final PathCondition[] pathConditions,
            @PluginElement("ScriptCondition") final ScriptCondition scriptCondition,
            @PluginConfiguration final Configuration config) {
            // @formatter:on
        final PathSorter sorter = sorterParameter == null ? new PathSortByModificationTime(true) : sorterParameter;
        return new DeleteAction(basePath, followLinks, maxDepth, testMode, sorter, pathConditions, scriptCondition,
                config.getStrSubstitutor());
    }
