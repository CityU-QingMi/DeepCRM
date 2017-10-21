    @Parameterized.Parameters(name = "")
    public static Collection<Object[]> data() {
        return Arrays.asList(
            new Object[][]{
                // { pattern, expected }
                { "0", "NameAbbreviatorTest" },
                { "1", "NameAbbreviatorTest" },
                { "2", "pattern.NameAbbreviatorTest" },
                { "3", "core.pattern.NameAbbreviatorTest" },
                { "1.", "o.a.l.l.c.p.NameAbbreviatorTest" },
                { "1.1.~", "o.a.~.~.~.~.NameAbbreviatorTest" },
                { ".", "......NameAbbreviatorTest" }
            }
        );
    }
