    @Test
    public void testPartial_UnsplitCodepoint()
    {
        Utf8PartialBuilder utf8 = new Utf8PartialBuilder();

        String seq1 = "Hello-\uC2B5@\uC39F\uC3A4";
        String seq2 = "\uC3BC\uC3A0\uC3A1-UTF-8!!";

        String ret1 = utf8.toPartialString(BufferUtil.toBuffer(seq1,StandardCharsets.UTF_8));
        String ret2 = utf8.toPartialString(BufferUtil.toBuffer(seq2,StandardCharsets.UTF_8));

        assertThat("Seq1",ret1,is(seq1));
        assertThat("Seq2",ret2,is(seq2));
    }
