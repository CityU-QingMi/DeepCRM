    public Object invoke(Object proxy, Method method, Object[] args)
        throws Throwable {
        if (method.getName().equals("setAutoCommit")) {
            setAutoCommit(((Boolean)args[0]).booleanValue());
        } else if (method.getName().equals("setTransactionIsolation")) {
            setTransactionIsolation(((Integer)args[0]).intValue());
        } else if (method.getName().equals("close")) {
            close();
        } else {
            try {
                return method.invoke(conn, args);
            }
            catch(InvocationTargetException ite) {
                throw (ite.getCause() != null ? ite.getCause() : ite);
            }
            
        }
        
        return null;
    }
