    @Parameters
    public static Collection<Case[]> data() throws Exception
    {
        List<Case[]> data = new ArrayList<>();

        Case.add(data,CloseSocket.class).expect("onClose()");
        Case.add(data,CloseReasonSocket.class).expect("onClose(CloseReason)");
        Case.add(data,CloseSessionSocket.class).expect("onClose(Session)");
        Case.add(data,CloseReasonSessionSocket.class).expect("onClose(CloseReason,Session)");
        Case.add(data,CloseSessionReasonSocket.class).expect("onClose(Session,CloseReason)");
        Case.add(data,CloseEndpointConfigSocket.class).expect("onClose(EndpointConfig)");

        return data;
    }
