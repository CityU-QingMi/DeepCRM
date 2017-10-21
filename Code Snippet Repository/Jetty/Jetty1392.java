    @Test
    public void testStreamBigBlockOneByteAtATime() throws Exception
    {
        String data = "0123456789ABCDEF";
        for (int i = 0; i < 10; ++i)
            data += data;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        GZIPOutputStream output = new GZIPOutputStream(baos);
        output.write(data.getBytes(StandardCharsets.UTF_8));
        output.close();
        byte[] bytes = baos.toByteArray();

        baos = new ByteArrayOutputStream();
        GZIPInputStream input = new GZIPInputStream(new ByteArrayInputStream(bytes), 1);
        int read;
        while ((read = input.read()) >= 0)
            baos.write(read);
        assertEquals(data, new String(baos.toByteArray(), StandardCharsets.UTF_8));
    }
