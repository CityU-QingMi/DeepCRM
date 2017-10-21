    @Deprecated
    public static ConsoleAppender createAppender(
            // @formatter:off
            Layout<? extends Serializable> layout,
            final Filter filter,
            Target target,
            final String name,
            final boolean follow,
            final boolean direct,
            final boolean ignoreExceptions) {
            // @formatter:on
        if (name == null) {
            LOGGER.error("No name provided for ConsoleAppender");
            return null;
        }
        if (layout == null) {
            layout = PatternLayout.createDefaultLayout();
        }
        target = target == null ? Target.SYSTEM_OUT : target;
        if (follow && direct) {
            LOGGER.error("Cannot use both follow and direct on ConsoleAppender");
            return null;
        }
        return new ConsoleAppender(name, layout, filter, getManager(target, follow, direct, layout), ignoreExceptions, target);
    }
