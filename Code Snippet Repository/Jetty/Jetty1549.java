    @Test
    public void testParse()
    {
        HttpURI uri = new HttpURI();

        uri.parse("*");
        assertThat(uri.getHost(),nullValue());
        assertThat(uri.getPath(),is("*"));
        
        uri.parse("/foo/bar");
        assertThat(uri.getHost(),nullValue());
        assertThat(uri.getPath(),is("/foo/bar"));
        
        uri.parse("//foo/bar");
        assertThat(uri.getHost(),is("foo"));
        assertThat(uri.getPath(),is("/bar"));
        
        uri.parse("http://foo/bar");
        assertThat(uri.getHost(),is("foo"));
        assertThat(uri.getPath(),is("/bar"));
    }
