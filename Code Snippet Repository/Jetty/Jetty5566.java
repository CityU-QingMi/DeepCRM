    @Test
    public void test10() throws Exception
    {
        MultiReleaseJarFile jarFile = new MultiReleaseJarFile(example,10,false);
        assertThat(jarFile.getEntry("META-INF/MANIFEST.MF").getVersion(), is(0));
        assertThat(jarFile.getEntry("org/example/OnlyInBase.class").getVersion(), is(0));
        assertThat(jarFile.getEntry("org/example/InBoth.class").getVersion(), is(10));
        assertThat(jarFile.getEntry("org/example/OnlyIn9.class").getVersion(), is(9));
        assertThat(jarFile.getEntry("org/example/onlyIn9/OnlyIn9.class").getVersion(), is(9));
        assertThat(jarFile.getEntry("org/example/In10Only.class").getVersion(), is(10));

        assertThat(jarFile.stream().count(), is(6L));
    }
