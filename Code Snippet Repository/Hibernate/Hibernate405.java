    public static String extractEntityName(Object object) {
        if ( Proxy.isProxyClass( object.getClass() ) ) {
            InvocationHandler handler = Proxy.getInvocationHandler(
                object
            );
            if ( DataProxyHandler.class.isAssignableFrom( handler.getClass() ) ) {
                DataProxyHandler myHandler = (DataProxyHandler) handler;
                return myHandler.getEntityName();
            }
        }
        return null;
    }
