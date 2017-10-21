    @Override
    public boolean process(Param param, JsrCallable callable) throws InvalidSignatureException
    {
        if (super.process(param,callable))
        {
            // Found common roles
            return true;
        }

        if (param.type.isAssignableFrom(ByteBuffer.class))
        {
            param.bind(Role.MESSAGE_BINARY);
            callable.setDecodingType(ByteBuffer.class);
            return true;
        }

        if (param.type.isAssignableFrom(byte[].class))
        {
            param.bind(Role.MESSAGE_BINARY);
            callable.setDecodingType(byte[].class);
            return true;
        }

        // Streaming
        if (param.type.isAssignableFrom(InputStream.class))
        {
            assertPartialMessageSupportDisabled(param,callable);
            param.bind(Role.MESSAGE_BINARY_STREAM);
            callable.setDecodingType(InputStream.class);
            return true;
        }

        return false;
    }
