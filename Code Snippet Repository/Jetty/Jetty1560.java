    @Test
    public void testEmpty()
    {
        QuotedCSV values = new QuotedCSV();
        values.addValue(",aaaa,  , bbbb ,,cccc,");
        Assert.assertThat(values,Matchers.contains(
                "aaaa",
                "bbbb",
                "cccc"));
    }
