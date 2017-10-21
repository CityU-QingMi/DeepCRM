    @Test
    public void testAppendGetClearAppendGet()
    {
        int bufsize = 128;
        ByteBuffer buf = ByteBuffer.allocate(bufsize);
        Utf8CharBuffer utf = Utf8CharBuffer.wrap(buf);

        int expectedSize = bufsize / 2;
        Assert.assertThat("Remaining (initial)",utf.remaining(),is(expectedSize));

        byte hellobytes[] = asUTF("Hello World");

        utf.append(hellobytes,0,hellobytes.length);
        ByteBuffer hellobuf = utf.getByteBuffer();

        Assert.assertThat("Remaining (after append)",utf.remaining(),is(expectedSize - hellobytes.length));
        Assert.assertThat("Hello buffer",asString(hellobuf),is("Hello World"));

        utf.clear();

        Assert.assertThat("Remaining (after clear)",utf.remaining(),is(expectedSize));

        byte whatnowbytes[] = asUTF("What Now?");
        utf.append(whatnowbytes,0,whatnowbytes.length);
        ByteBuffer whatnowbuf = utf.getByteBuffer();

        Assert.assertThat("Remaining (after 2nd append)",utf.remaining(),is(expectedSize - whatnowbytes.length));
        Assert.assertThat("What buffer",asString(whatnowbuf),is("What Now?"));
    }
