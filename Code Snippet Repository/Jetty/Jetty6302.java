    public static EchoCase add(List<EchoCase[]> data, Class<?> serverPojo)
    {
        EchoCase ecase = new EchoCase();
        ecase.serverPojo = serverPojo;
        data.add(new EchoCase[]
        { ecase });
        ServerEndpoint endpoint = serverPojo.getAnnotation(ServerEndpoint.class);
        ecase.path = endpoint.value();
        return ecase;
    }
