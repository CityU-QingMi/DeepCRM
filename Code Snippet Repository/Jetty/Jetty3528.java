    @SuppressWarnings("")
    private List<InclusiveByteRange> parseRanges(String rangeString, int size)
    {
        Vector strings = new Vector();
        strings.add(rangeString);

        List<InclusiveByteRange> ranges;
        ranges = InclusiveByteRange.satisfiableRanges(strings.elements(),size);
        assertNotNull("Satisfiable Ranges should not be null",ranges);
        return ranges;
    }
