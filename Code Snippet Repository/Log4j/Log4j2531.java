    @Test
    public void testPositionalParamWithIndexGap_VariableRangeIndexNoGap() throws Exception {
        class NoGap {
            @Parameters(index = "0..*") String[] str0;
            @Parameters(index = "3") String str2;
        }
        NoGap noGap = CommandLine.populateCommand(new NoGap(),"val0", "val1", "val2", "val3");
        assertArrayEquals(new String[] {"val0", "val1", "val2", "val3"}, noGap.str0);
        assertEquals("val3", noGap.str2);
    }
