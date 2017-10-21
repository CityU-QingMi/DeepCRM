    protected void assertDecoderRequired()
    {
        if (getDecoder() == null)
        {
            StringBuilder err = new StringBuilder();
            err.append("Unable to find a valid ");
            err.append(Decoder.class.getName());
            err.append(" for parameter #");
            Param param = params[idxMessageObject];
            err.append(param.index);
            err.append(" [").append(param.type).append("] in method: ");
            err.append(ReflectUtils.toString(pojo,method));
            throw new InvalidSignatureException(err.toString());
        }
    }
