    @Override
    protected List<EncoderMetadata> discover(Class<? extends Encoder> encoder)
    {
        List<EncoderMetadata> metadatas = new ArrayList<>();

        if (Encoder.Binary.class.isAssignableFrom(encoder))
        {
            Class<?> objType = getEncoderType(encoder,Encoder.Binary.class);
            metadatas.add(new EncoderMetadata(encoder,objType,MessageType.BINARY,false));
        }
        if (Encoder.BinaryStream.class.isAssignableFrom(encoder))
        {
            Class<?> objType = getEncoderType(encoder,Encoder.BinaryStream.class);
            metadatas.add(new EncoderMetadata(encoder,objType,MessageType.BINARY,true));
        }
        if (Encoder.Text.class.isAssignableFrom(encoder))
        {
            Class<?> objType = getEncoderType(encoder,Encoder.Text.class);
            metadatas.add(new EncoderMetadata(encoder,objType,MessageType.TEXT,false));
        }
        if (Encoder.TextStream.class.isAssignableFrom(encoder))
        {
            Class<?> objType = getEncoderType(encoder,Encoder.TextStream.class);
            metadatas.add(new EncoderMetadata(encoder,objType,MessageType.TEXT,true));
        }

        if (!ReflectUtils.isDefaultConstructable(encoder))
        {
            throw new InvalidSignatureException("Encoder must have public, no-args constructor: " + encoder.getName());
        }

        if (metadatas.size() <= 0)
        {
            throw new InvalidSignatureException("Not a valid Encoder class: " + encoder.getName() + " implements no " + Encoder.class.getName() + " interfaces");
        }

        return metadatas;
    }
