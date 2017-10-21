    public void addBean(Node.UseBean n, String s, String type, String scope)
        throws JasperException {

        if (!(scope == null || scope.equals("page") || scope.equals("request") 
                || scope.equals("session") || scope.equals("application"))) {
            errDispatcher.jspError(n, "jsp.error.usebean.badScope");
        }

        beanTypes.put(s, type);
    }
