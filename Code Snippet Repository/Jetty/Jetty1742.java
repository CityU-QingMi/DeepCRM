    @Test
    public void testGatherWrite() throws Exception
    {
        File dir = MavenTestingUtils.getTargetTestingDir();
        if (!dir.exists())
            dir.mkdir();

        File file = File.createTempFile("test",".txt",dir);
        file.deleteOnExit();
        FileChannel out = FileChannel.open(file.toPath(),
                StandardOpenOption.CREATE,
                StandardOpenOption.READ,
                StandardOpenOption.WRITE,
                StandardOpenOption.DELETE_ON_CLOSE);

        ByteBuffer[] buffers = new ByteBuffer[4096];
        long expected=0;
        for (int i=0;i<buffers.length;i++)
        {
            buffers[i]=BufferUtil.toBuffer(i);
            expected+=buffers[i].remaining();
        }

        long wrote = IO.write(out,buffers,0,buffers.length);

        assertEquals(expected,wrote);

        for (int i=0;i<buffers.length;i++)
            assertEquals(0,buffers[i].remaining());
    }
