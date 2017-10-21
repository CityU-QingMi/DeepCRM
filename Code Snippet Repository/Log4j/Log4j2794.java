    @Override
    public void insertObject(final NoSqlObject<Map<String, Object>> object) {
        try {
            final Response response = this.client.save(object.unwrap());
            if (Strings.isNotEmpty(response.getError())) {
                throw new AppenderLoggingException(
                        "Failed to write log event to CouchDB due to error: " + response.getError() + '.');
            }
        } catch (final Exception e) {
            throw new AppenderLoggingException("Failed to write log event to CouchDB due to error: " + e.getMessage(),
                    e);
        }
    }
