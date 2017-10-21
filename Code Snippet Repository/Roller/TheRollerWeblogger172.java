    private Map initializeMissingProps(Map<String, RuntimeConfigProperty> props) {

        if(props == null) {
            props = new HashMap<String, RuntimeConfigProperty>();
        }

        // start by getting our runtimeConfigDefs
        RuntimeConfigDefs runtimeConfigDefs =
                WebloggerRuntimeConfig.getRuntimeConfigDefs();

        // can't do initialization without our config defs
        if(runtimeConfigDefs == null) {
            return props;
        }

        // iterate through all the definitions and add properties
        // that are not already in our props map

        for (ConfigDef configDef : runtimeConfigDefs.getConfigDefs()) {
            for (DisplayGroup dGroup : configDef.getDisplayGroups()) {
                for (PropertyDef propDef : dGroup.getPropertyDefs()) {

                    // do we already have this prop?  if not then add it
                    if(!props.containsKey(propDef.getName())) {
                        RuntimeConfigProperty newprop =
                                new RuntimeConfigProperty(
                                        propDef.getName(), propDef.getDefaultValue());

                        props.put(propDef.getName(), newprop);

                        log.info("Property " + propDef.getName() +
                            " not yet in roller_properties database table, will store with " +
                            "default value of [" + propDef.getDefaultValue() + "`]");
                    }
                }
            }
        }

        return props;
    }
