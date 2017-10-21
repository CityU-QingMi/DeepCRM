    @Test
    public void testIO() throws Exception
    {
        // Only a little test
        ByteArrayInputStream in = new ByteArrayInputStream("The quick brown fox jumped over the lazy dog".getBytes());
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        IO.copy(in, out);

        assertEquals("copyThread", out.toString(), "The quick brown fox jumped over the lazy dog");
    }
