    @Test
    public void testParseString() throws Exception
    {
        HttpURI httpUri = new HttpURI(input);
        
        try
        {
            new URI(input);
            // URI is valid (per java.net.URI parsing)
            
            // Test case sanity check
            assertThat("[" + input + "] expected path (test case) cannot be null",path,notNullValue());

            // Assert expectations
            assertThat("[" + input + "] .scheme",httpUri.getScheme(),is(scheme));
            assertThat("[" + input + "] .host",httpUri.getHost(),is(host));
            assertThat("[" + input + "] .port",httpUri.getPort(),is(port == null ? -1 : Integer.parseInt(port)));
            assertThat("[" + input + "] .path",httpUri.getPath(),is(path));
            assertThat("[" + input + "] .param",httpUri.getParam(),is(param));
            assertThat("[" + input + "] .query",httpUri.getQuery(),is(query));
            assertThat("[" + input + "] .fragment",httpUri.getFragment(),is(fragment));
            assertThat("[" + input + "] .toString",httpUri.toString(),is(input));
        }
        catch (URISyntaxException e)
        {
            // Assert HttpURI values for invalid URI (such as "//")
            assertThat("[" + input + "] .scheme",httpUri.getScheme(),is(nullValue()));
            assertThat("[" + input + "] .host",httpUri.getHost(),is(nullValue()));
            assertThat("[" + input + "] .port",httpUri.getPort(),is(-1));
            assertThat("[" + input + "] .path",httpUri.getPath(),is(nullValue()));
            assertThat("[" + input + "] .param",httpUri.getParam(),is(nullValue()));
            assertThat("[" + input + "] .query",httpUri.getQuery(),is(nullValue()));
            assertThat("[" + input + "] .fragment",httpUri.getFragment(),is(nullValue()));
        }
    }
