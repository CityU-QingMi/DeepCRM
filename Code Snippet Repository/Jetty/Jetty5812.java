    @Test
    public void testResourceFromStringForString()
            throws Exception
    {
        Assume.assumeTrue(JDK.IS_9);

        URI string_loc = TypeUtil.getLocationOfClass(String.class);
        Resource resource = Resource.newResource(string_loc.toASCIIString());

        assertThat(resource.exists(), is(true));
        assertThat(resource.isDirectory(), is(false));
        assertThat(IO.readBytes(resource.getInputStream()).length,Matchers.greaterThan(0));
        assertThat(IO.readBytes(resource.getInputStream()).length,is((int)resource.length()));
        assertThat(resource.getWeakETag("-xxx"),startsWith("W/\""));
        assertThat(resource.getWeakETag("-xxx"),endsWith("-xxx\""));
    }
