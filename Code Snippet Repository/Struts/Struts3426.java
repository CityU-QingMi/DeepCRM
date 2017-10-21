    protected boolean containsBean(String beanName) {
        ServiceReference[] refs = bundleAccessor.getAllServiceReferences(SPRING_SERVICE_NAME);
        if (refs != null) {
            for (ServiceReference ref : refs) {
                Object context = bundleAccessor.getService(ref);
                if (OsgiUtil.containsBean(context, beanName))
                    return true;
            }
        }

        return false;
    }
