    @SuppressWarnings("")
    @Test
    public void testIncludedLocationsOrModule() throws Exception
    {
        Assume.assumeTrue(JDK.IS_9);

        // jar from JVM classloader
        URI mod_string = TypeUtil.getLocationOfClass(String.class);
        // System.err.println(mod_string);

        // a jar from maven repo jar
        URI loc_junit = TypeUtil.getLocationOfClass(Test.class);
        // System.err.println(loc_junit);

        // class file
        URI loc_test = TypeUtil.getLocationOfClass(ClasspathPatternTest.class);
        // System.err.println(loc_test);

        ClasspathPattern pattern = new ClasspathPattern();
        pattern.include("something");
        Assert.assertThat(pattern.match(String.class), Matchers.is(false));
        Assert.assertThat(pattern.match(Test.class), Matchers.is(false));
        Assert.assertThat(pattern.match(JDK.class), Matchers.is(false));
        Assert.assertThat(pattern.match(ClasspathPatternTest.class), Matchers.is(false));

        // Add module for all JVM base classes
        pattern.include("jrt:/java.base");

        // Add jar for individual class and classes directory
        pattern.include(loc_junit.toString(), loc_test.toString());

        Assert.assertThat(pattern.match(String.class), Matchers.is(true));
        Assert.assertThat(pattern.match(Test.class), Matchers.is(true));
        Assert.assertThat(pattern.match(JDK.class), Matchers.is(false));
        Assert.assertThat(pattern.match(ClasspathPatternTest.class), Matchers.is(true));

        pattern.add("-java.lang.String");
        Assert.assertThat(pattern.match(String.class), Matchers.is(false));
        Assert.assertThat(pattern.match(Test.class), Matchers.is(true));
        Assert.assertThat(pattern.match(JDK.class), Matchers.is(false));
        Assert.assertThat(pattern.match(ClasspathPatternTest.class), Matchers.is(true));
    }
