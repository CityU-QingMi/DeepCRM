    @Override
    protected List<DecoderMetadata> discover(Class<? extends Decoder> decoder)
    {
        List<DecoderMetadata> metadatas = new ArrayList<>();

        if (Decoder.Binary.class.isAssignableFrom(decoder))
        {
            Class<?> objType = getDecoderType(decoder,Decoder.Binary.class);
            metadatas.add(new DecoderMetadata(decoder,objType,MessageType.BINARY,false));
        }
        if (Decoder.BinaryStream.class.isAssignableFrom(decoder))
        {
            Class<?> objType = getDecoderType(decoder,Decoder.BinaryStream.class);
            metadatas.add(new DecoderMetadata(decoder,objType,MessageType.BINARY,true));
        }
        if (Decoder.Text.class.isAssignableFrom(decoder))
        {
            Class<?> objType = getDecoderType(decoder,Decoder.Text.class);
            metadatas.add(new DecoderMetadata(decoder,objType,MessageType.TEXT,false));
        }
        if (Decoder.TextStream.class.isAssignableFrom(decoder))
        {
            Class<?> objType = getDecoderType(decoder,Decoder.TextStream.class);
            metadatas.add(new DecoderMetadata(decoder,objType,MessageType.TEXT,true));
        }

        if (!ReflectUtils.isDefaultConstructable(decoder))
        {
            throw new InvalidSignatureException("Decoder must have public, no-args constructor: " + decoder.getName());
        }

        if (metadatas.size() <= 0)
        {
            throw new InvalidSignatureException("Not a valid Decoder class: " + decoder.getName());
        }

        return metadatas;
    }
