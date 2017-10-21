    public String getDefaultClassRef() {
        if ((defaultClassRef == null) && !parents.isEmpty()) {
            for (PackageConfig parent : parents) {
                String parentDefault = parent.getDefaultClassRef();
                if (parentDefault != null) {
                    return parentDefault;
                }
            }
        }
        return defaultClassRef;
    }
