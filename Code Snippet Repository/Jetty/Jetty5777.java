    @Test
    public void testNonExistantSymlink() throws Exception
    {
        Path dir = testdir.getPath().normalize().toRealPath();
        Files.createDirectories(dir);
        
        Path foo = dir.resolve("foo");
        Path bar = dir.resolve("bar");
        
        try
        {
            Files.createSymbolicLink(bar,foo);
        }
        catch (UnsupportedOperationException | FileSystemException e)
        {
            // if unable to create symlink, no point testing the rest
            // this is the path that Microsoft Windows takes.
            assumeNoException(e);
        }
        
        try (Resource base = newResource(dir.toFile()))
        {
            // FileResource does not pass this test!
            assumeFalse(base instanceof FileResource);
            
            Resource resFoo = base.addPath("foo");
            Resource resBar = base.addPath("bar");
            
            assertThat("resFoo.uri", resFoo.getURI(), is(foo.toUri()));
            
            // Access to the same resource, but via a symlink means that they are not equivalent
            assertThat("foo.equals(bar)", resFoo.equals(resBar), is(false));
            
            assertThat("resource.alias", resFoo, hasNoAlias());
            assertThat("resource.uri.alias", newResource(resFoo.getURI()), hasNoAlias());
            assertThat("resource.file.alias", newResource(resFoo.getFile()), hasNoAlias());
            
            assertThat("alias", resBar, isAliasFor(resFoo));
            assertThat("uri.alias", newResource(resBar.getURI()), isAliasFor(resFoo));
            assertThat("file.alias", newResource(resBar.getFile()), isAliasFor(resFoo));
        }
    }
