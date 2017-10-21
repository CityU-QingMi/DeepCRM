    public void actualAsyncLog(final RingBufferLogEvent event) {
        final List<Property> properties = privateConfig.loggerConfig.getPropertyList();

        if (properties != null) {
            StringMap contextData = (StringMap) event.getContextData();
            if (contextData.isFrozen()) {
                final StringMap temp = ContextDataFactory.createContextData();
                temp.putAll(contextData);
                contextData = temp;
            }
            for (int i = 0; i < properties.size(); i++) {
                final Property prop = properties.get(i);
                if (contextData.getValue(prop.getName()) != null) {
                    continue; // contextMap overrides config properties
                }
                final String value = prop.isValueNeedsLookup() //
                        ? privateConfig.config.getStrSubstitutor().replace(event, prop.getValue()) //
                        : prop.getValue();
                contextData.putValue(prop.getName(), value);
            }
            event.setContextData(contextData);
        }

        final ReliabilityStrategy strategy = privateConfig.loggerConfig.getReliabilityStrategy();
        strategy.log(this, event);
    }
