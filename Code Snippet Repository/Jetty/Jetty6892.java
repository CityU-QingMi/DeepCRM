    @Test
    public void testSimpleGetBuffer()
    {
        int bufsize = 64;
        ByteBuffer buf = ByteBuffer.allocate(bufsize);
        Utf8CharBuffer utf = Utf8CharBuffer.wrap(buf);

        int expectedSize = bufsize / 2;
        Assert.assertThat("Remaining (initial)",utf.remaining(),is(expectedSize));

        byte bb[] = asUTF("Hello World");
        utf.append(bb,0,bb.length);

        expectedSize -= bb.length;
        Assert.assertThat("Remaining (after append)",utf.remaining(),is(expectedSize));

        ByteBuffer actual = utf.getByteBuffer();
        Assert.assertThat("Buffer length",actual.remaining(),is(bb.length));

        Assert.assertThat("Message",asString(actual),is("Hello World"));
    }
