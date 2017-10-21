    @Test
    public void testPartial_SplitCodepoint_WithNoBuf()
    {
        Utf8PartialBuilder utf8 = new Utf8PartialBuilder();

        String seq1 = "48656C6C6F2DEC8AB540EC8E9FEC8E";
        String seq2 = "A4EC8EBCEC8EA0EC8EA12D5554462D382121";
        
        String ret1 = utf8.toPartialString(toByteBuffer(seq1));
        String ret2 = utf8.toPartialString(BufferUtil.EMPTY_BUFFER);
        String ret3 = utf8.toPartialString(toByteBuffer(seq2));

        assertThat("Seq1",ret1,is("Hello-\uC2B5@\uC39F"));
        assertThat("Seq2",ret2,is(""));
        assertThat("Seq3",ret3,is("\uC3A4\uC3BC\uC3A0\uC3A1-UTF-8!!"));
    }
