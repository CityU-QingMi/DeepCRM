    @Test
    public void testJarSource() throws Exception
    {
        assertThat(URIUtil.getJarSource("file:///tmp/"),is("file:///tmp/"));
        assertThat(URIUtil.getJarSource("jar:file:///tmp/foo.jar"),is("file:///tmp/foo.jar"));
        assertThat(URIUtil.getJarSource("jar:file:///tmp/foo.jar!/some/path"),is("file:///tmp/foo.jar"));
        assertThat(URIUtil.getJarSource(new URI("file:///tmp/")),is(new URI("file:///tmp/")));
        assertThat(URIUtil.getJarSource(new URI("jar:file:///tmp/foo.jar")),is(new URI("file:///tmp/foo.jar")));
        assertThat(URIUtil.getJarSource(new URI("jar:file:///tmp/foo.jar!/some/path")),is(new URI("file:///tmp/foo.jar")));
    }
