    @Test
    public void testGermanUmlauts() throws Exception
    {
        byte[] bytes = new byte[6];
        bytes[0] = (byte)0xC3;
        bytes[1] = (byte)0xBC;
        bytes[2] = (byte)0xC3;
        bytes[3] = (byte)0xB6;
        bytes[4] = (byte)0xC3;
        bytes[5] = (byte)0xA4;

        Utf8StringBuffer buffer = new Utf8StringBuffer();
        for (int i = 0; i < bytes.length; i++)
            buffer.append(bytes[i]);

        assertEquals("\u00FC\u00F6\u00E4",buffer.toString());
    }
