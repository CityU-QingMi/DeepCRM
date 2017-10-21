    public void mapFunction(String fnQName, final Class c,
            final String methodName, final Class[] args) {
        java.lang.reflect.Method method;
        if (SecurityUtil.isPackageProtectionEnabled()) {
            try {
                method = (java.lang.reflect.Method) AccessController
                        .doPrivileged(new PrivilegedExceptionAction() {

                            public Object run() throws Exception {
                                return c.getDeclaredMethod(methodName, args);
                            }
                        });
            } catch (PrivilegedActionException ex) {
                throw new RuntimeException(
                        "Invalid function mapping - no such method: "
                                + ex.getException().getMessage());
            }
        } else {
            try {
                method = c.getDeclaredMethod(methodName, args);
            } catch (NoSuchMethodException e) {
                throw new RuntimeException(
                        "Invalid function mapping - no such method: "
                                + e.getMessage());
            }
        }

        this.fnmap.put(fnQName, method);
    }
