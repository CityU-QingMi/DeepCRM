    @Test
    public void testPositionalParamWithFixedIndexRange() {
        class App {
            @Parameters(index = "0..1") File file0_1;
            @Parameters(index = "1..2", type = File.class) List<File> fileList1_2;
            @Parameters(index = "0..3") File[] fileArray0_3 = new File[4];
            @Parameters List<String> all;
        }
        App app1 = CommandLine.populateCommand(new App(), "000", "111", "222", "333");
        assertEquals("field initialized with arg[0]", new File("000"), app1.file0_1);
        assertEquals("arg[1] and arg[2]", Arrays.asList(
                new File("111"),
                new File("222")), app1.fileList1_2);
        assertArrayEquals("arg[0-3]", new File[]{
                null, null, null, null, // existing values
                new File("000"),
                new File("111"),
                new File("222"),
                new File("333")}, app1.fileArray0_3);
        assertEquals("args", Arrays.asList("000", "111", "222", "333"), app1.all);

        App app2 = CommandLine.populateCommand(new App(), "000", "111");
        assertEquals("field initialized with arg[0]", new File("000"), app2.file0_1);
        assertEquals("arg[1]", Arrays.asList(new File("111")), app2.fileList1_2);
        assertArrayEquals("arg[0-3]", new File[]{
                null, null, null, null, // existing values
                new File("000"),
                new File("111"),}, app2.fileArray0_3);
        assertEquals("args", Arrays.asList("000", "111"), app2.all);

        App app3 = CommandLine.populateCommand(new App(), "000");
        assertEquals("field initialized with arg[0]", new File("000"), app3.file0_1);
        assertEquals("arg[1]", new ArrayList<File>(), app3.fileList1_2);
        assertArrayEquals("arg[0-3]", new File[]{
                null, null, null, null, // existing values
                new File("000")}, app3.fileArray0_3);
        assertEquals("args", Arrays.asList("000"), app3.all);

        try {
            CommandLine.populateCommand(new App());
            fail("Should fail with missingParamException");
        } catch (MissingParameterException ex) {
            assertEquals("Missing required parameter: file0_1", ex.getMessage());
        }
    }
