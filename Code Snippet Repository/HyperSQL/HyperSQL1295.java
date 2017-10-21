    static private RefCapablePropertyResourceBundle getRef(String baseName,
            ResourceBundle rb, ClassLoader loader) {
        if (!(rb instanceof PropertyResourceBundle))
            throw new MissingResourceException(
                    "Found a Resource Bundle, but it is a "
                            + rb.getClass().getName(),
                    PropertyResourceBundle.class.getName(), null);
        if (allBundles.containsKey(rb)) return allBundles.get(rb);
        RefCapablePropertyResourceBundle newPRAFP =
                new RefCapablePropertyResourceBundle(baseName,
                        (PropertyResourceBundle) rb, loader);
        allBundles.put(rb, newPRAFP);
        return newPRAFP;
    }
