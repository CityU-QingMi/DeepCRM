    public void config(Props props)
    {
        String prefix = "jetty.graph.";
        colorModuleBg = getProperty(props,prefix + "color.module.bg",colorModuleBg);
        colorEnabledBg = getProperty(props,prefix + "color.enabled.bg",colorEnabledBg);
        colorTransitiveBg = getProperty(props,prefix + "color.transitive.bg",colorTransitiveBg);
        colorCellBg = getProperty(props,prefix + "color.cell.bg",colorCellBg);
        colorHeaderBg = getProperty(props,prefix + "color.header.bg",colorHeaderBg);
        colorModuleFont = getProperty(props,prefix + "color.font",colorModuleFont);
    }
