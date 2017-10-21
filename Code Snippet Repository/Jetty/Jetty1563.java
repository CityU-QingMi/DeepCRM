    @Test
    public void testParamsOnly()
    {
        QuotedCSV values = new QuotedCSV(false);
        values.addValue("for=192.0.2.43, for=\"[2001:db8:cafe::17]\", for=unknown");
        assertThat(values,Matchers.contains(
                "for=192.0.2.43",
                "for=[2001:db8:cafe::17]",
                "for=unknown"));
    }
