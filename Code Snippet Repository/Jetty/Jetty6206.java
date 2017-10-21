    @Override
    public boolean willDecode(ByteBuffer bytes)
    {
        if (bytes == null)
        {
            return false;
        }
        int id = bytes.get(bytes.position());
        return (id != FruitBinaryEncoder.FRUIT_ID_BYTE);
    }
