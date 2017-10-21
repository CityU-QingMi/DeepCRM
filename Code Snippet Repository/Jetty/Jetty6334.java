    @Parameters
    public static Collection<Class<?>[]> data()
    {
        List<Class<?>[]> data = new ArrayList<>();

        // @formatter:off
        data.add(new Class<?>[]{ InvalidCloseIntSocket.class, OnClose.class });
        data.add(new Class<?>[]{ InvalidErrorErrorSocket.class, OnError.class });
        data.add(new Class<?>[]{ InvalidErrorExceptionSocket.class, OnError.class });
        data.add(new Class<?>[]{ InvalidErrorIntSocket.class, OnError.class });
        data.add(new Class<?>[]{ InvalidOpenCloseReasonSocket.class, OnOpen.class });
        data.add(new Class<?>[]{ InvalidOpenIntSocket.class, OnOpen.class });
        data.add(new Class<?>[]{ InvalidOpenSessionIntSocket.class, OnOpen.class });
        // @formatter:on

        // TODO: invalid return types
        // TODO: static methods
        // TODO: private or protected methods
        // TODO: abstract methods

        return data;
    }
