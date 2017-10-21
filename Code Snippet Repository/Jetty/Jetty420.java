    @Override
    public void onContent(Response response, ByteBuffer content)
    {
        int length = content.remaining();
        if (length > BufferUtil.space(buffer))
        {
            int requiredCapacity = buffer == null ? 0 : buffer.capacity() + length;
            if (requiredCapacity > maxLength)
                response.abort(new IllegalArgumentException("Buffering capacity exceeded"));

            int newCapacity = Math.min(Integer.highestOneBit(requiredCapacity) << 1, maxLength);
            buffer = BufferUtil.ensureCapacity(buffer, newCapacity);
        }
        BufferUtil.append(buffer, content);
    }
