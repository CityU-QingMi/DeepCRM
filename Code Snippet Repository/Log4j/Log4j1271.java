    @Override
    public PatternFormatter[] getFormatters(final LogEvent event) {
        final SimpleBindings bindings = new SimpleBindings();
        bindings.putAll(configuration.getProperties());
        bindings.put("substitutor", configuration.getStrSubstitutor());
        bindings.put("logEvent", event);
        final Object object = configuration.getScriptManager().execute(script.getName(), bindings);
        if (object == null) {
            return defaultFormatters;
        }
        final PatternFormatter[] patternFormatter = formatterMap.get(object.toString());

        return patternFormatter == null ? defaultFormatters : patternFormatter;
    }
