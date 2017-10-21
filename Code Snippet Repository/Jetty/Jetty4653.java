    @Test
    public void testAsDashDCommandLineArg() throws IOException, InterruptedException
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
        commands.add("-Dtest.foo=bar"); // TESTING THIS
        commands.add(bogusXml.getAbsolutePath());

        // Run command, collect output
        String output = collectRunOutput(commands);
        
        // Test for values
        Assert.assertThat(output,containsString("test.foo=bar"));
    }
