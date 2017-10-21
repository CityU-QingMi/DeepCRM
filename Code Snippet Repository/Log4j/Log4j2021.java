    @Test
    public void testLogger() throws Exception {
        final Logger logger = this.ctx.getLogger(LOGGER_NAME);
        assertThat(logger, is(instanceOf(org.apache.logging.log4j.core.Logger.class)));
        final org.apache.logging.log4j.core.Logger l = (org.apache.logging.log4j.core.Logger) logger;
        assertThat(l.getLevel(), is(equalTo(Level.DEBUG)));
        assertThat(l.filterCount(), is(equalTo(1)));
        final Iterator<Filter> iterator = l.getFilters();
        assertThat(iterator.hasNext(), is(true));
        final Filter filter = iterator.next();
        assertThat(filter, is(instanceOf(ThreadContextMapFilter.class)));
        final Map<String, Appender> appenders = l.getAppenders();
        assertThat(appenders, is(notNullValue()));
        assertThat(appenders.size(), is(equalTo(1)));
        final Appender appender = appenders.get(APPENDER_NAME);
        assertThat(appender, is(notNullValue()));
        assertThat(appender.getName(), is(equalTo("STDOUT")));
    }
