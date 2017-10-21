    @Override
    protected boolean commitAndClose() {
        boolean closed = true;
        try {
            if (this.connection != null && !this.connection.isClosed()) {
                if (this.isBatchSupported) {
                    this.statement.executeBatch();
                }
                this.connection.commit();
            }
        } catch (final SQLException e) {
            throw new AppenderLoggingException("Failed to commit transaction logging event or flushing buffer.", e);
        } finally {
            try {
                Closer.close(this.statement);
            } catch (final Exception e) {
                logWarn("Failed to close SQL statement logging event or flushing buffer", e);
                closed = false;
            } finally {
                this.statement = null;
            }

            try {
                Closer.close(this.connection);
            } catch (final Exception e) {
                logWarn("Failed to close database connection logging event or flushing buffer", e);
                closed = false;
            } finally {
                this.connection = null;
            }
        }
        return closed;
    }
