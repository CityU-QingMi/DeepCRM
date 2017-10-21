    @SuppressWarnings("")
    private InclusiveByteRange parseRange(String rangeString, int size)
    {
        Vector strings = new Vector();
        strings.add(rangeString);

        List ranges = InclusiveByteRange.satisfiableRanges(strings.elements(),size);
        assertNotNull("Satisfiable Ranges should not be null",ranges);
        assertEquals("Satisfiable Ranges of [" + rangeString + "] count",1,ranges.size());
        return (InclusiveByteRange)ranges.get(0);
    }
