    @Override
    public Configuration getConfiguration(final LoggerContext loggerContext, final ConfigurationSource source) {
        final ConfigurationBuilder<BuiltConfiguration> builder;
        try (final InputStream configStream = source.getInputStream()) {
            builder = new Log4j1ConfigurationParser().buildConfigurationBuilder(configStream);
        } catch (final IOException e) {
            throw new ConfigurationException("Unable to load " + source, e);
        }
        return builder.build();
    }
