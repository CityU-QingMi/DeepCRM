    @Test
    public void testPositionalParamWithIndexGap_RangeIndexNoGap() throws Exception {
        class NoGap {
            @Parameters(index = "0..1") String[] str0;
            @Parameters(index = "2") String str2;
        }
        NoGap noGap = CommandLine.populateCommand(new NoGap(),"val0", "val1", "val2");
        assertArrayEquals(new String[] {"val0", "val1"}, noGap.str0);
        assertEquals("val2", noGap.str2);
    }
