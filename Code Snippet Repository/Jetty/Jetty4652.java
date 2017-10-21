    @Test
    public void testAsCommandLineArg() throws IOException, InterruptedException
    {
        File bogusXml = MavenTestingUtils.getTestResourceFile("bogus.xml");

        // Setup command line
        List<String> commands = new ArrayList<>();
        commands.add(getJavaBin());
        commands.add("-Dmain.class=" + PropertyDump.class.getName());
        commands.add("-cp");
        commands.add(getClassPath());
        // addDebug(commands);
        commands.add(getStartJarBin());
        commands.add("test.foo=bar"); // TESTING THIS
        commands.add(bogusXml.getAbsolutePath());

        // Run command, collect output
        String output = collectRunOutput(commands);

        // Test for values
        Assert.assertThat("output",output,containsString("foo=bar"));
    }
