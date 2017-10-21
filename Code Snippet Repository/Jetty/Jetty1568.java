    @Test
    public void testEmpty()
    {
        QuotedQualityCSV values = new QuotedQualityCSV();
        values.addValue(",aaaa,  , bbbb ,,cccc,");
        Assert.assertThat(values,Matchers.contains(
                "aaaa",
                "bbbb",
                "cccc"));
    }
