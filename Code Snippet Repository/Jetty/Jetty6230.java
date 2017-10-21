    @Override
    public ByteBuffer encode(Fruit fruit) throws EncodeException
    {
        int len = 1; // id byte
        len += STRLEN_STORAGE + fruit.name.length();
        len += STRLEN_STORAGE + fruit.color.length();

        ByteBuffer buf = ByteBuffer.allocate(len + 64);
        buf.flip();
        buf.put(FRUIT_ID_BYTE);
        putString(buf,fruit.name);
        putString(buf,fruit.color);
        buf.flip();

        return buf;
    }
