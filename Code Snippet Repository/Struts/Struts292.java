    @Inject(value = XWorkConstants.OGNL_EXCLUDED_CLASSES, required = false)
    public void setExcludedClasses(String commaDelimitedClasses) {
        Set<String> classNames = TextParseUtil.commaDelimitedStringToSet(commaDelimitedClasses);
        Set<Class<?>> classes = new HashSet<>();

        for (String className : classNames) {
            try {
                classes.add(Class.forName(className));
            } catch (ClassNotFoundException e) {
                throw new ConfigurationException("Cannot load excluded class: " + className, e);
            }
        }

        excludedClasses = Collections.unmodifiableSet(classes);
    }
