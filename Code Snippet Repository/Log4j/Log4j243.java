        protected Class<?> getCallerClass(final String fqcn, final String pkg) {
            boolean next = false;
            for (final Class<?> clazz : getClassContext()) {
                if (fqcn.equals(clazz.getName())) {
                    next = true;
                    continue;
                }
                if (next && clazz.getName().startsWith(pkg)) {
                    return clazz;
                }
            }
            // TODO: return Object.class
            return null;
        }
