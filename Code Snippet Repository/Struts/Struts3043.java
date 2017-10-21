    public static void introspecthelper(Object bean, String prop,
                                        String value, ServletRequest request,
                                        String param, boolean ignoreMethodNF)
            throws JasperException {
        if (Constants.IS_SECURITY_ENABLED) {
            try {
                PrivilegedIntrospectHelper dp =
                        new PrivilegedIntrospectHelper(
                                bean, prop, value, request, param, ignoreMethodNF);
                AccessController.doPrivileged(dp);
            } catch (PrivilegedActionException pe) {
                Exception e = pe.getException();
                throw (JasperException) e;
            }
        } else {
            internalIntrospecthelper(
                    bean, prop, value, request, param, ignoreMethodNF);
        }
    }
