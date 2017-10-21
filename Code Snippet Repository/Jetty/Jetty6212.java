    @Parameters
    public static Collection<Case[]> data() throws Exception
    {
        List<Case[]> data = new ArrayList<>();
        Field fOpen = findFieldRef(AnnotatedEndpointMetadata.class,"onOpen");
        Field fClose = findFieldRef(AnnotatedEndpointMetadata.class,"onClose");
        Field fError = findFieldRef(AnnotatedEndpointMetadata.class,"onError");
        Field fText = findFieldRef(AnnotatedEndpointMetadata.class,"onText");
        Field fBinary = findFieldRef(AnnotatedEndpointMetadata.class,"onBinary");
        Field fBinaryStream = findFieldRef(AnnotatedEndpointMetadata.class,"onBinaryStream");
        Field fPong = findFieldRef(AnnotatedEndpointMetadata.class,"onPong");

        // @formatter:off
        // -- Open Events
        Case.add(data, BasicOpenSocket.class, fOpen);
        Case.add(data, BasicOpenSessionSocket.class, fOpen, Session.class);
        // -- Close Events
        Case.add(data, CloseSocket.class, fClose);
        Case.add(data, CloseReasonSocket.class, fClose, CloseReason.class);
        Case.add(data, CloseReasonSessionSocket.class, fClose, CloseReason.class, Session.class);
        Case.add(data, CloseSessionReasonSocket.class, fClose, Session.class, CloseReason.class);
        // -- Error Events
        Case.add(data, BasicErrorSocket.class, fError);
        Case.add(data, BasicErrorSessionSocket.class, fError, Session.class);
        Case.add(data, BasicErrorSessionThrowableSocket.class, fError, Session.class, Throwable.class);
        Case.add(data, BasicErrorThrowableSocket.class, fError, Throwable.class);
        Case.add(data, BasicErrorThrowableSessionSocket.class, fError, Throwable.class, Session.class);
        // -- Text Events
        Case.add(data, BasicTextMessageStringSocket.class, fText, String.class);
        // -- Binary Events
        Case.add(data, BasicBinaryMessageByteBufferSocket.class, fBinary, ByteBuffer.class);
        // -- Pong Events
        Case.add(data, BasicPongMessageSocket.class, fPong, PongMessage.class);
        // -- InputStream Events
        Case.add(data, BasicInputStreamSocket.class, fBinaryStream, InputStream.class);
        Case.add(data, BasicInputStreamWithThrowableSocket.class, fBinaryStream, InputStream.class);
        // @formatter:on

        // TODO: validate return types

        return data;
    }
