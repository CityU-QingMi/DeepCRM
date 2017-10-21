    public SelectedClassnameClassLoader( String[] includedClasses,
                                         String[] excludedClasses,
                                         String[] notFoundClasses,
                                         ClassLoader parent ) {
        super(parent);
        this.includedClasses = includedClasses;
        this.excludedClasses = excludedClasses;
        this.notFoundClasses = notFoundClasses;

        log.debug("created " + this);
    }
