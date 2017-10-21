    public InputStream buildInputStream() {
        final InputStream in = Objects.requireNonNull(this.inputStream, "inputStream");
        if (this.buffered) {
            if (this.bufferSize > 0) {
                return new LoggerBufferedInputStream(in, this.charset, this.bufferSize, this.logger, this.fqcn,
                    this.level, this.marker);
            }
            return new LoggerBufferedInputStream(in, this.charset, this.logger, this.fqcn, this.level, this.marker);
        }
        return new LoggerInputStream(in, this.charset, this.logger, this.fqcn, this.level, this.marker);
    }
