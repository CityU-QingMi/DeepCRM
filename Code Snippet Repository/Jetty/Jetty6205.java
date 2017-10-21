    @Override
    public Fruit decode(ByteBuffer bytes) throws DecodeException
    {
        try
        {
            int id = bytes.get(bytes.position());
            if (id != FruitBinaryEncoder.FRUIT_ID_BYTE)
            {
                // not a binary fruit object
                throw new DecodeException(bytes,"Not an encoded Binary Fruit object");
            }

            Fruit fruit = new Fruit();
            fruit.name = getUTF8String(bytes);
            fruit.color = getUTF8String(bytes);
            return fruit;
        }
        catch (BufferUnderflowException e)
        {
            throw new DecodeException(bytes,"Unable to read Fruit from binary message",e);
        }
    }
