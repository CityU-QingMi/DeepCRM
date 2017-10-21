    @Test
    public void testMultipleRangesClipped()
    {
        String rangeString;

        rangeString = "bytes=5-20,35-65,-5";

        List<InclusiveByteRange> ranges = parseRanges(rangeString,50);
        assertEquals("Satisfiable Ranges of [" + rangeString + "] count",3,ranges.size());
        assertRange("Range [" + rangeString + "]",5,20,50,ranges.get(0));
        assertRange("Range [" + rangeString + "]",35,49,50,ranges.get(1));
        assertRange("Range [" + rangeString + "]",45,49,50,ranges.get(2));
    }
