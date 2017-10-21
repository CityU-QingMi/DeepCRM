    @Override
    public boolean process(Param param, JsrCallable callable) throws InvalidSignatureException
    {
        if (param.type.isAssignableFrom(metadata.getObjectType()))
        {
            assertPartialMessageSupportDisabled(param,callable);

            switch (metadata.getMessageType())
            {
                case TEXT:
                    if (metadata.isStreamed())
                    {
                        param.bind(Role.MESSAGE_TEXT_STREAM);
                    }
                    else
                    {
                        param.bind(Role.MESSAGE_TEXT);
                    }
                    break;
                case BINARY:
                    if (metadata.isStreamed())
                    {
                        param.bind(Role.MESSAGE_BINARY_STREAM);
                    }
                    else
                    {
                        param.bind(Role.MESSAGE_BINARY);
                    }
                    break;
                case PONG:
                    param.bind(Role.MESSAGE_PONG);
                    break;
            }

            callable.setDecodingType(metadata.getObjectType());
            return true;
        }
        return false;
    }
