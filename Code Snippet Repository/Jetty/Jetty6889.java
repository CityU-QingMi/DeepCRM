    @Test
    public void testAppendGetAppendGet()
    {
        ByteBuffer buf = ByteBuffer.allocate(128);
        Utf8CharBuffer utf = Utf8CharBuffer.wrap(buf);

        byte hellobytes[] = asUTF("Hello ");
        byte worldbytes[] = asUTF("World!");

        utf.append(hellobytes, 0, hellobytes.length);
        ByteBuffer hellobuf = utf.getByteBuffer();
        utf.append(worldbytes, 0, worldbytes.length);
        ByteBuffer worldbuf = utf.getByteBuffer();

        Assert.assertThat("Hello buffer",asString(hellobuf),is("Hello "));
        Assert.assertThat("World buffer",asString(worldbuf),is("Hello World!"));
    }
