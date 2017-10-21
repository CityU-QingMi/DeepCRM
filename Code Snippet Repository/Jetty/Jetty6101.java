    public Param getMessageObjectParam()
    {
        if (idxMessageObject < 0)
        {
            idxMessageObject = findMessageObjectIndex();

            if (idxMessageObject < 0)
            {
                StringBuilder err = new StringBuilder();
                err.append("A message type must be specified [TEXT, BINARY, DECODER, or PONG] : ");
                err.append(ReflectUtils.toString(pojo,method));
                throw new InvalidSignatureException(err.toString());
            }
        }

        return super.params[idxMessageObject];
    }
