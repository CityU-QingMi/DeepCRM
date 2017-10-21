    @Before
    public void setupReader() {
        final Reader srcReader = new StringReader("a\nb\nc\nd");
        this.logReader = (BufferedReader)
            IoBuilder.forLogger(getLogger())
                .filter(srcReader)
                .setLevel(Level.WARN)
                .setBuffered(true)
                .buildReader();
    }
