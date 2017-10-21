    @Override
    public void append(LogEvent event) {
        if (rewritePolicy != null) {
            event = rewritePolicy.rewrite(event);
        }
        final String pattern = routes.getPattern(event, scriptStaticVariables);
        final String key = pattern != null ? configuration.getStrSubstitutor().replace(event, pattern) : defaultRoute.getKey();
        final AppenderControl control = getControl(key, event);
        if (control != null) {
            control.callAppender(event);
        }

        if (purgePolicy != null) {
            purgePolicy.update(key, event);
        }
    }
