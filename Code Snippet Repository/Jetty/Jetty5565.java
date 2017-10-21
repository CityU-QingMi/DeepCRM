    @Test
    public void test9() throws Exception
    {
        MultiReleaseJarFile jarFile = new MultiReleaseJarFile(example,9,false);
        assertThat(jarFile.getEntry("META-INF/MANIFEST.MF").getVersion(), is(0));
        assertThat(jarFile.getEntry("org/example/OnlyInBase.class").getVersion(), is(0));
        assertThat(jarFile.getEntry("org/example/InBoth$InnerBoth.class").getVersion(), is(9));
        assertThat(jarFile.getEntry("org/example/InBoth.class").getVersion(), is(9));
        assertThat(jarFile.getEntry("org/example/OnlyIn9.class").getVersion(), is(9));
        assertThat(jarFile.getEntry("org/example/onlyIn9/OnlyIn9.class").getVersion(), is(9));
        assertThat(jarFile.getEntry("org/example/InBoth$Inner9.class").getVersion(), is(9));

        assertThat(jarFile.stream().count(), is(7L));
    }
