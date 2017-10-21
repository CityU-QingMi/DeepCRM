    public static ProtectedFunctionMapper getMapForFunction(String fnQName,
            final Class c, final String methodName, final Class[] args) {
        java.lang.reflect.Method method;
        ProtectedFunctionMapper funcMapper;
        if (SecurityUtil.isPackageProtectionEnabled()) {
            funcMapper = (ProtectedFunctionMapper) AccessController
                    .doPrivileged(new PrivilegedAction() {
                        public Object run() {
                            return new ProtectedFunctionMapper();
                        }
                    });

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
            funcMapper = new ProtectedFunctionMapper();
            try {
                method = c.getDeclaredMethod(methodName, args);
            } catch (NoSuchMethodException e) {
                throw new RuntimeException(
                        "Invalid function mapping - no such method: "
                                + e.getMessage());
            }
        }
        funcMapper.theMethod = method;
        return funcMapper;
    }
