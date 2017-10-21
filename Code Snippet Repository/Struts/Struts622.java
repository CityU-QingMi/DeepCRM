    protected Map getTooltipConfig(UIBean component) {
        Object tooltipConfigObj = component.getParameters().get("tooltipConfig");
        Map<String, String> tooltipConfig = new LinkedHashMap<>();

        if (tooltipConfigObj instanceof Map) {
            // we get this if its configured using
            // 1] UI component's tooltipConfig attribute  OR
            // 2] <param name="tooltip" value="" /> param tag value attribute

            tooltipConfig = new LinkedHashMap<>((Map) tooltipConfigObj);
        } else if (tooltipConfigObj instanceof String) {

            // we get this if its configured using
            // <param name="tooltipConfig"> ... </param> tag's body
            String tooltipConfigStr = (String) tooltipConfigObj;
            String[] tooltipConfigArray = tooltipConfigStr.split("\\|");

            for (String aTooltipConfigArray : tooltipConfigArray) {
                String[] configEntry = aTooltipConfigArray.trim().split("=");
                String key = configEntry[0].trim();
                String value;
                if (configEntry.length > 1) {
                    value = configEntry[1].trim();
                    tooltipConfig.put(key, value);
                } else {
                    LOG.warn("component {} tooltip config param {} has no value defined, skipped", component, key);
                }
            }
        }
        if (component.javascriptTooltip != null)
            tooltipConfig.put("jsTooltipEnabled", component.javascriptTooltip);
        if (component.tooltipIconPath != null)
            tooltipConfig.put("tooltipIcon", component.tooltipIconPath);
        if (component.tooltipDelay != null)
            tooltipConfig.put("tooltipDelay", component.tooltipDelay);
        return tooltipConfig;
    }
