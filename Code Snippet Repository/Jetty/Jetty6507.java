    public static WebSocketFrame copy(Frame original)
    {
        WebSocketFrame copy;
        switch (original.getOpCode())
        {
            case OpCode.BINARY:
                copy = new BinaryFrame();
                break;
            case OpCode.TEXT:
                copy = new TextFrame();
                break;
            case OpCode.CLOSE:
                copy = new CloseFrame();
                break;
            case OpCode.CONTINUATION:
                copy = new ContinuationFrame();
                break;
            case OpCode.PING:
                copy = new PingFrame();
                break;
            case OpCode.PONG:
                copy = new PongFrame();
                break;
            default:
                throw new IllegalArgumentException("Cannot copy frame with opcode " + original.getOpCode() + " - " + original);
        }

        copy.copyHeaders(original);
        ByteBuffer payload = original.getPayload();
        if (payload != null)
        {
            ByteBuffer payloadCopy = ByteBuffer.allocate(payload.remaining());
            payloadCopy.put(payload.slice()).flip();
            copy.setPayload(payloadCopy);
        }
        return copy;
    }
