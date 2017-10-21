    @Test
    public void testParseRequestTarget()
    {
        HttpURI uri = new HttpURI();

        uri.parseRequestTarget("GET","*");
        assertThat(uri.getHost(),nullValue());
        assertThat(uri.getPath(),is("*"));
        
        uri.parseRequestTarget("GET","/foo/bar");
        assertThat(uri.getHost(),nullValue());
        assertThat(uri.getPath(),is("/foo/bar"));
        
        uri.parseRequestTarget("GET","//foo/bar");
        assertThat(uri.getHost(),nullValue());
        assertThat(uri.getPath(),is("//foo/bar"));
        
        uri.parseRequestTarget("GET","http://foo/bar");
        assertThat(uri.getHost(),is("foo"));
        assertThat(uri.getPath(),is("/bar"));
    }
