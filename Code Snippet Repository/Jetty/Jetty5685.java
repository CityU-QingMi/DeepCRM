    @Test
    public void testWildHttpRequestParse()
    {
        // Arbitrary Http Response Headers seen in the wild.
        // Request URI -> http://www.eclipse.org/jetty/
        List<String> expected = new ArrayList<>();
        expected.add("HEAD /jetty/ HTTP/1.0");
        expected.add("User-Agent: \"Mozilla/5.0 (X11; U; Linux i686; en-US; rv:1.8.1.6) Gecko/20060601 Firefox/2.0.0.6 (Ubuntu-feisty)\"");
        expected.add("Accept: */*");
        expected.add("Host: www.eclipse.org");
        expected.add("Connection: Keep-Alive");
        expected.add("");

        // Prepare Buffer
        ByteBuffer buf = ByteBuffer.allocate(512);
        for (String line : expected)
        {
            appendUtf8(buf,line + "\r\n");
        }

        BufferUtil.flipToFlush(buf,0);

        // Parse Buffer
        Utf8LineParser utfparser = new Utf8LineParser();

        List<String> actual = new ArrayList<>();
        int count = 0;
        int excessive = expected.size() + 10; // fail-safe for bad code
        boolean done = false;
        while (!done)
        {
            String line = utfparser.parse(buf);
            if (line != null)
            {
                actual.add(line);
            }
            else
            {
                done = true;
            }
            count++;
            Assert.assertThat("Parse Count is excessive (bug in code!)",count,lessThan(excessive));
        }

        // Validate Results
        assertEquals(expected,actual);
    }
