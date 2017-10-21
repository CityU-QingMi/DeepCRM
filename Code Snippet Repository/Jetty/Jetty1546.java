    @Test
    public void testParseURI() throws Exception
    {
        URI javaUri = null;
        try
        {
            javaUri = new URI(input);
            assumeNotNull(javaUri);
        }
        catch (URISyntaxException e)
        {
            // Ignore, as URI is invalid anyway
            assumeNoException(e);
        }
        
        HttpURI httpUri = new HttpURI(javaUri);

        assertThat("[" + input + "] .scheme",httpUri.getScheme(),is(scheme));
        assertThat("[" + input + "] .host",httpUri.getHost(),is(host));
        assertThat("[" + input + "] .port",httpUri.getPort(),is(port == null ? -1 : Integer.parseInt(port)));
        assertThat("[" + input + "] .path",httpUri.getPath(),is(path));
        assertThat("[" + input + "] .param",httpUri.getParam(),is(param));
        assertThat("[" + input + "] .query",httpUri.getQuery(),is(query));
        assertThat("[" + input + "] .fragment",httpUri.getFragment(),is(fragment));
        
        assertThat("[" + input + "] .toString",httpUri.toString(),is(input));
    }
