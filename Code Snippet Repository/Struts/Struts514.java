    public String lookupRegisteredValidatorType(String name) {
        // lookup the validator class mapped to the type name
        String className = validators.get(name);

        if (className == null) {
            throw new IllegalArgumentException("There is no validator class mapped to the name " + name);
        }

        return className;
    }
