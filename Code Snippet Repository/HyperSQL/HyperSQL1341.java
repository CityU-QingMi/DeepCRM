    public ValidatingResourceBundle(
            String baseName, Class<? extends Enum<?>> enumType) {
        this.enumType = enumType;
        try {
            wrappedRCPRB = RefCapablePropertyResourceBundle.getBundle(baseName,
                    enumType.getClassLoader());
            validate();
        } catch (RuntimeException re) {
            System.err.println("Failed to initialize resource bundle: " + re);
            // Make extra sure that the source of this fatal startup condition
            // is not hidden.
            throw re;
        }
    }
