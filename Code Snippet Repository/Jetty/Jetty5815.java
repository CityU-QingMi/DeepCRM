    @Test
    public void testResourceAllModules()
            throws Exception
    {
        Assume.assumeTrue(JDK.IS_9);

        Resource resource = Resource.newResource("jrt:/");

        assertThat(resource.exists(), is(false));
        assertThat(resource.isDirectory(), is(false));
        assertThat(resource.length(),is(-1L));
    }
