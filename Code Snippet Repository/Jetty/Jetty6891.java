    @Test
    public void testAppendUnicodeGetBuffer()
    {
        ByteBuffer buf = ByteBuffer.allocate(64);
        Utf8CharBuffer utf = Utf8CharBuffer.wrap(buf);

        byte bb[] = asUTF("Hello A\u00ea\u00f1\u00fcC");
        utf.append(bb,0,bb.length);

        ByteBuffer actual = utf.getByteBuffer();
        Assert.assertThat("Buffer length should be retained",actual.remaining(),is(bb.length));
        Assert.assertThat("Message",asString(actual),is("Hello A\u00ea\u00f1\u00fcC"));
    }
