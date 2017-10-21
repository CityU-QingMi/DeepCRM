    @Test
    public void testGet_GenericListInput()
    {
        List<String> input = new ArrayList<String>();
        input.add("a");
        assertEquals("a",LazyList.get(input,0));

        List<URI> uris = new ArrayList<URI>();
        uris.add(URI.create("http://www.mortbay.org/"));
        uris.add(URI.create("http://jetty.codehaus.org/jetty/"));
        uris.add(URI.create("http://www.intalio.com/jetty/"));
        uris.add(URI.create("http://www.eclipse.org/jetty/"));

        // Make sure that Generics pass through the 'get' routine safely.
        // We should be able to call this without casting the result to URI
        URI eclipseUri = LazyList.get(uris, 3);
        assertEquals("http://www.eclipse.org/jetty/", eclipseUri.toASCIIString());
    }
