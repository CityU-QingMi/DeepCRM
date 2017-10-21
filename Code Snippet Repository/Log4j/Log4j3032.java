    @Deprecated
    public static ServletAppender createAppender(final Layout<? extends Serializable> layout, final Filter filter,
            final String name, final boolean ignoreExceptions) {
        // @formatter:off
    	return newBuilder()
    			.withFilter(filter)
    			.withIgnoreExceptions(ignoreExceptions)
    			.withLayout(layout)
    			.withName(name)
    			.build();
    	// @formatter:on
    }
