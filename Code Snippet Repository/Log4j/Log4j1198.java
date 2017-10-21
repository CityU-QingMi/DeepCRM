    @Override
    public String getConfigText(final String charsetName) throws IOException {
        try {
            final ConfigurationSource source = loggerContext.getConfiguration().getConfigurationSource();
            final ConfigurationSource copy = source.resetInputStream();
            final Charset charset = Charset.forName(charsetName);
            return readContents(copy.getInputStream(), charset);
        } catch (final Exception ex) {
            final StringWriter sw = new StringWriter(BUFFER_SIZE);
            ex.printStackTrace(new PrintWriter(sw));
            return sw.toString();
        }
    }
