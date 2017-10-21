    private Object invoke(String objectName, String method, Object[] params, String[] types) throws Exception {
        MBeanServer server = (MBeanServer) MBeanServerFactory.findMBeanServer(null).get(0);
        ObjectName mbean = new ObjectName(objectName);

        if (server == null) {
            throw new Exception("Can't find mbean server");
        }

        getLog().info("invoking " + method);
        return server.invoke(mbean, method, params, types);
    }
