    @Test
    public void testLogToFile() throws Exception {
        final Logger logger = this.ctx.getLogger(FILE_LOGGER_NAME);
        final long random = this.random.nextLong();
        logger.debug("This is test message number {}", random);
        int count = 0;
        String line = Strings.EMPTY;
        try (final BufferedReader in = new BufferedReader(new FileReader(this.logFileName))) {
            while (in.ready()) {
                ++count;
                line = in.readLine();
            }
        }
        assertThat(count, is(equalTo(1)));
        assertThat(line, endsWith(Long.toString(random)));
    }
