    @Test
    public void testInflateBasics() throws Exception
    {
        // should result in "info:" text if properly inflated
        byte rawbuf[] = TypeUtil.fromHexString("CaCc4bCbB70200"); // what pywebsocket produces
        // byte rawbuf[] = TypeUtil.fromHexString("CbCc4bCbB70200"); // what java produces

        Inflater inflater = new Inflater(true);
        inflater.reset();
        inflater.setInput(rawbuf, 0, rawbuf.length);

        byte outbuf[] = new byte[64];
        int len = inflater.inflate(outbuf);
        inflater.end();
        Assert.assertThat("Inflated length", len, greaterThan(4));

        String actual = StringUtil.toUTF8String(outbuf, 0, len);
        Assert.assertThat("Inflated text", actual, is("info:"));
    }
