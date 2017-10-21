    public void validate() {
        String val;
        if (validated) return;
        validated = true;
        Set<String> resKeysFromEls = new HashSet<String>();
        for (Enum<?> e : enumType.getEnumConstants())
            resKeysFromEls.add(e.toString());
        Enumeration<String> allKeys = wrappedRCPRB.getKeys();
        while (allKeys.hasMoreElements()) {
            // We can't test positional parameters, but we can verify that
            // referenced files exist by reading the values.
            // Pretty inefficient, but this can be optimized when I have time.
            val = allKeys.nextElement();
            wrappedRCPRB.getString(val);  // because it throws if missing?
            // Keep no reference to the returned String
            resKeysFromEls.remove(val);
        }
        if (resKeysFromEls.size() > 0)
            throw new RuntimeException(
                    "Resource Bundle pre-validation failed.  "
                    + "Missing property with key:  " + resKeysFromEls);
    }
