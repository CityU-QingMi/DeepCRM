    @Test
    public void testBase() throws Exception
    {
        MultiReleaseJarFile jarFile = new MultiReleaseJarFile(example,8,false);
        assertThat(jarFile.getEntry("META-INF/MANIFEST.MF").getVersion(), is(0));
        assertThat(jarFile.getEntry("org/example/OnlyInBase.class").getVersion(), is(0));
        assertThat(jarFile.getEntry("org/example/InBoth$InnerBase.class").getVersion(), is(0));
        assertThat(jarFile.getEntry("org/example/InBoth$InnerBoth.class").getVersion(), is(0));
        assertThat(jarFile.getEntry("org/example/InBoth.class").getVersion(), is(0));

        assertThat(jarFile.stream().count(), is(5L));
    }
