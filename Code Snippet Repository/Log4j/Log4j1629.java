    @Test
    public void testConfiguration() {
        final Configuration configuration = context.getConfiguration();
        assertNotNull(configuration);
        final FileAppender appender = configuration.getAppender("info");
        assertNotNull(appender);
        final CompositeFilter compFilter = (CompositeFilter) appender.getFilter();
        assertNotNull(compFilter);
        final Filter[] filters = compFilter.getFiltersArray();
        assertNotNull(filters);
        boolean foundLevel = false;
        for (final Filter filter : filters) {
            final ThresholdFilter tFilter = (ThresholdFilter) filter;
            if (infom1Level.equals(tFilter.getLevel())) {
                foundLevel = true;
                break;
            }
        }
        Assert.assertTrue("Level not found: " + infom1Level, foundLevel);
    }
