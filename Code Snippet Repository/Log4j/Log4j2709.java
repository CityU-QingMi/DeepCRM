    public Reader buildReader() {
        final Reader in = Objects.requireNonNull(this.reader, "reader");
        if (this.buffered) {
            if (this.bufferSize > 0) {
                return new LoggerBufferedReader(in, this.bufferSize, this.logger, this.fqcn, this.level, this.marker);
            }
            return new LoggerBufferedReader(in, this.logger, this.fqcn, this.level, this.marker);
        }
        return new LoggerReader(in, this.logger, this.fqcn, this.level, this.marker);
    }
