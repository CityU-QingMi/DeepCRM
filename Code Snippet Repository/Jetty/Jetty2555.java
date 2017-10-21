    @Test
    public void testHeaderOverwriteValues() throws IOException
    {
        String headers[][] = {
                { "size", "100" },
                { "size", "200" },
                { "size", "300" },
                { "size", "400" },
                { "size", "500" },
                { "title", "abc" },
                { "title", "bac" },
                { "title", "cba" },
                { "title1", "abba" },
                { "title1", "abba1" },
                { "title1", "abba" },
                { "title1", "abba1" }
        };
        assertHeaders(headers);

        Iterator<String> e = _response.getHeaders("size").iterator();
        int count = 0;
        while (e.hasNext())
        {
            e.next();
            count++;
        }

        assertEquals(1, count);
        assertEquals("500", _response.getHeader("size"));
        assertEquals("cba", _response.getHeader("title"));
        assertEquals("abba1", _response.getHeader("title1"));
    }
