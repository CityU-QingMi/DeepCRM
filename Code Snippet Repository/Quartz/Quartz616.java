    public void setAllowsTransientData(boolean allowsTransientData) {
    
        if (containsTransientData() && !allowsTransientData) {
            throw new IllegalStateException(
                "Cannot set property 'allowsTransientData' to 'false' "
                    + "when data map contains non-serializable objects.");
        }
    
        this.allowsTransientData = allowsTransientData;
    }
