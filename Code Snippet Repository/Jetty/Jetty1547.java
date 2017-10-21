    @Test
    public void testCompareToJavaNetURI() throws Exception
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
        
        assertThat("[" + input + "] .scheme",httpUri.getScheme(),is(javaUri.getScheme()));
        assertThat("[" + input + "] .host",httpUri.getHost(),is(javaUri.getHost()));
        assertThat("[" + input + "] .port",httpUri.getPort(),is(javaUri.getPort()));
        assertThat("[" + input + "] .path",httpUri.getPath(),is(javaUri.getRawPath()));
        // Not Relevant for java.net.URI -- assertThat("["+input+"] .param", httpUri.getParam(), is(param));
        assertThat("[" + input + "] .query",httpUri.getQuery(),is(javaUri.getRawQuery()));
        assertThat("[" + input + "] .fragment",httpUri.getFragment(),is(javaUri.getFragment()));
        assertThat("[" + input + "] .toString",httpUri.toString(),is(javaUri.toASCIIString()));
    }
