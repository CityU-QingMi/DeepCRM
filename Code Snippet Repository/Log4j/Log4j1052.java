    public StatusConfiguration withDestination(final String destination) {
        try {
            this.destination = parseStreamName(destination);
        } catch (final URISyntaxException e) {
            this.error("Could not parse URI [" + destination + "]. Falling back to default of stdout.");
            this.destination = DEFAULT_STREAM;
        } catch (final FileNotFoundException e) {
            this.error("File could not be found at [" + destination + "]. Falling back to default of stdout.");
            this.destination = DEFAULT_STREAM;
        }
        return this;
    }
