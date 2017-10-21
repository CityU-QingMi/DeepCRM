    public void sendMessage(String... msgs) throws IOException
    {
        int len = 0;
        for (String msg : msgs)
        {
            len += (msg.length() + 2);
        }

        ByteBuffer buf = ByteBuffer.allocate(len);

        for (String msg : msgs)
        {
            buf.put((byte)0x00);
            buf.put(msg.getBytes(StandardCharsets.UTF_8));
            buf.put((byte)0xFF);
        }

        BufferUtil.writeTo(buf,out);
        out.flush();
    }
