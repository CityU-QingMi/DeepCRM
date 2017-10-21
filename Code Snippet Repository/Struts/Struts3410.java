    public ServiceReference[] getAllServiceReferences(String className) {
        if (bundleContext != null) {
            try {
                return bundleContext.getServiceReferences(className, null);
            } catch (InvalidSyntaxException e) {
                //cannot happen we are passing null as the param
                if (LOG.isErrorEnabled()) {
                    LOG.error("Invalid syntax for service lookup", e);
                }
            }
        }

        return null;
    }
