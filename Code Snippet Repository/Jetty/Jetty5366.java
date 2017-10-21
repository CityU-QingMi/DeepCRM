    private void testWriteToWithBufferThatDoesNotExposeArray(int capacity) throws IOException
    {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] bytes = new byte[capacity];
        ThreadLocalRandom.current().nextBytes(bytes);
        ByteBuffer buffer = BufferUtil.allocate(capacity);
        BufferUtil.append(buffer, bytes, 0, capacity);
        BufferUtil.writeTo(buffer.asReadOnlyBuffer(), out);
        assertThat("Bytes in out equal bytes in buffer", Arrays.equals(bytes, out.toByteArray()), is(true));
    }
