        protected Class<?> getCallerClass(final Class<?> anchor) {
            boolean next = false;
            for (final Class<?> clazz : getClassContext()) {
                if (anchor.equals(clazz)) {
                    next = true;
                    continue;
                }
                if (next) {
                    return clazz;
                }
            }
            return Object.class;
        }
