    @Test
    public void testPositionalParamsUnknownArgumentMultiValueWithUnmatchedArgsAllowed() throws Exception {
        class SingleValue {
            @Parameters(index = "0..2") String[] str;
        }
        CommandLine cmd = new CommandLine(new SingleValue()).setUnmatchedArgumentsAllowed(true);
        cmd.parse("val0", "val1", "val2", "val3");
        assertArrayEquals(new String[]{"val0", "val1", "val2"}, ((SingleValue)cmd.getCommand()).str);
        assertEquals(Arrays.asList("val3"), cmd.getUnmatchedArguments());
    }
