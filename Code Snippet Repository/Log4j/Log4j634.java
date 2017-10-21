    @Deprecated
    public static ConsoleAppender createAppender(Layout<? extends Serializable> layout,
            final Filter filter,
            final String targetStr,
            final String name,
            final String follow,
            final String ignore) {
        if (name == null) {
            LOGGER.error("No name provided for ConsoleAppender");
            return null;
        }
        if (layout == null) {
            layout = PatternLayout.createDefaultLayout();
        }
        final boolean isFollow = Boolean.parseBoolean(follow);
        final boolean ignoreExceptions = Booleans.parseBoolean(ignore, true);
        final Target target = targetStr == null ? DEFAULT_TARGET : Target.valueOf(targetStr);
        return new ConsoleAppender(name, layout, filter, getManager(target, isFollow, false, layout), ignoreExceptions, target);
    }
