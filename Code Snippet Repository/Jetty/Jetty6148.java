    @SuppressWarnings("")
    @Override
    public void messageComplete()
    {
        finished = true;

        DecoderFactory.Wrapper decoder = msgWrapper.getDecoder();
        Decoder.Text<Object> textDecoder = (Decoder.Text<Object>)decoder.getDecoder();
        try
        {
            Object obj = textDecoder.decode(utf.toString());
            wholeHandler.onMessage(obj);
        }
        catch (DecodeException e)
        {
            throw new WebSocketException("Unable to decode text data",e);
        }
    }
