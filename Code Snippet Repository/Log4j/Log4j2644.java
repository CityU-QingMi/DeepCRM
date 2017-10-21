    private FlumeAppender(final String name, final Filter filter, final Layout<? extends Serializable> layout,
                          final boolean ignoreExceptions, final String includes, final String excludes,
                          final String required, final String mdcPrefix, final String eventPrefix,
                          final boolean compress, final FlumeEventFactory factory, final AbstractFlumeManager manager) {
        super(name, filter, layout, ignoreExceptions);
        this.manager = manager;
        this.mdcIncludes = includes;
        this.mdcExcludes = excludes;
        this.mdcRequired = required;
        this.eventPrefix = eventPrefix;
        this.mdcPrefix = mdcPrefix;
        this.compressBody = compress;
        this.factory = factory == null ? this : factory;
    }
