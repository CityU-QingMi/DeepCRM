    @Before
    public void setupStreams() {
        final InputStream srcInputStream = new ByteArrayInputStream("a\nb\nc\nd".getBytes());
        this.logIn = (BufferedInputStream)
            IoBuilder.forLogger(getLogger())
                .filter(srcInputStream)
                .setLevel(LEVEL)
                .setBuffered(true)
                .buildInputStream();
    }
