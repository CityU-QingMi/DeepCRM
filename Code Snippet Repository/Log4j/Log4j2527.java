    @Test
    public void testPositionalParamWithAbsoluteIndex() {
        class App {
            @Parameters(index = "0") File file0;
            @Parameters(index = "1") File file1;
            @Parameters(index = "2", arity = "0..1") File file2;
            @Parameters List<String> all;
        }
        App app1 = CommandLine.populateCommand(new App(), "000", "111", "222", "333");
        assertEquals("arg[0]", new File("000"), app1.file0);
        assertEquals("arg[1]", new File("111"), app1.file1);
        assertEquals("arg[2]", new File("222"), app1.file2);
        assertEquals("args", Arrays.asList("000", "111", "222", "333"), app1.all);

        App app2 = CommandLine.populateCommand(new App(), "000", "111");
        assertEquals("arg[0]", new File("000"), app2.file0);
        assertEquals("arg[1]", new File("111"), app2.file1);
        assertEquals("arg[2]", null, app2.file2);
        assertEquals("args", Arrays.asList("000", "111"), app2.all);

        try {
            CommandLine.populateCommand(new App(), "000");
            fail("Should fail with missingParamException");
        } catch (MissingParameterException ex) {
            assertEquals("Missing required parameter: file1", ex.getMessage());
        }
    }
