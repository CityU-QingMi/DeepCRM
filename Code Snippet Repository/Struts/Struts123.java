    public String getFullDefaultActionRef() {
        if ((defaultActionRef == null) && !parents.isEmpty()) {
            for (PackageConfig parent : parents) {
                String parentDefault = parent.getFullDefaultActionRef();

                if (parentDefault != null) {
                    return parentDefault;
                }
            }
        }
        return defaultActionRef;
    }
