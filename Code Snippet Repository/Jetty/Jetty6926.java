    public static void assertEquals(String message, ByteBuffer expectedBuffer, ByteBuffer actualBuffer)
    {
        if (expectedBuffer == null)
        {
            assertThat(message,actualBuffer,nullValue());
        }
        else
        {
            byte expectedBytes[] = BufferUtil.toArray(expectedBuffer);
            byte actualBytes[] = BufferUtil.toArray(actualBuffer);
            assertEquals(message,expectedBytes,actualBytes);
        }
    }
