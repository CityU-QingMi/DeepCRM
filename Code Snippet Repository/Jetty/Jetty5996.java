    @Before
    public void before()
    {
        _pattern.clear();
        _pattern.add("org.package.");
        _pattern.add("-org.excluded.");
        _pattern.add("org.example.FooBar");
        _pattern.add("-org.example.Excluded");
        _pattern.addAll(Arrays.asList(
                "-org.example.Nested$Minus",
                "org.example.Nested",
                "org.example.Nested$Something"));

        Assert.assertThat(_pattern, Matchers.containsInAnyOrder(
                "org.package.",
                "-org.excluded.",
                "org.example.FooBar",
                "-org.example.Excluded",
                "-org.example.Nested$Minus",
                "org.example.Nested",
                "org.example.Nested$Something"
        ));
    }
