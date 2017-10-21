    @Test
    public void testPackageMatch()
    {
        Assert.assertTrue(_pattern.match("org.package.Something"));
        Assert.assertTrue(_pattern.match("org.package.other.Something"));

        Assert.assertFalse(_pattern.match("org.example.Unknown"));
        Assert.assertFalse(_pattern.match("org.example.FooBar.Unknown"));
        Assert.assertFalse(_pattern.match("org.example.FooBarElse"));
    }
