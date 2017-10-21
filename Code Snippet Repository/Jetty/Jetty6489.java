    public CloseFrame asFrame()
    {
        CloseFrame frame = new CloseFrame();
        frame.setFin(true);
        // Frame forbidden codes result in no status code (and no reason string)
        if ((statusCode != StatusCode.NO_CLOSE) && (statusCode != StatusCode.NO_CODE) && (statusCode != StatusCode.FAILED_TLS_HANDSHAKE))
        {
            assertValidStatusCode(statusCode);
            frame.setPayload(asByteBuffer());
        }
        return frame;
    }
