    @SuppressWarnings("")
    @Override
    public void messageComplete()
    {
        super.finished = true;

        byte data[] = out.toByteArray();

        DecoderFactory.Wrapper decoder = msgWrapper.getDecoder();
        Decoder.Binary<Object> binaryDecoder = (Binary<Object>)decoder.getDecoder();
        try
        {
            Object obj = binaryDecoder.decode(BufferUtil.toBuffer(data));
            wholeHandler.onMessage(obj);
        }
        catch (DecodeException e)
        {
            throw new WebSocketException("Unable to decode binary data",e);
        }
    }
