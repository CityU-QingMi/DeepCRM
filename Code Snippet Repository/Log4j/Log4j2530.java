    @Ignore("")
    @Test
    public void testPositionalParamWithFixedIndexRangeAndVariableArity() throws Exception {
        class App {
            @Parameters(index = "0") InetAddress host1;
            @Parameters(index = "1") int port1;
            @Parameters(index = "2") InetAddress host2;
            @Parameters(index = "3..4", arity = "1..2") int[] port2range;
            @Parameters(index = "4..*") String[] files;
        }
        // now with only one arg in port2range
        App app2 = CommandLine.populateCommand(new App(), "localhost", "1111", "localhost", "2222", "file1", "file2");
        assertEquals(InetAddress.getByName("localhost"), app2.host1);
        assertEquals(1111, app2.port1);
        assertEquals(InetAddress.getByName("localhost"), app2.host2);
        assertArrayEquals(new int[]{2222}, app2.port2range);
        assertArrayEquals(new String[]{"file1", "file2"}, app2.files);
    }
