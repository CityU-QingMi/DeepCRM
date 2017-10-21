    public PrintStream buildPrintStream() {
        try {
            if (this.outputStream == null) {
                return new LoggerPrintStream(this.logger, this.autoFlush, this.charset, this.fqcn, this.level,
                    this.marker);
            }
            return new LoggerPrintStream(this.outputStream, this.autoFlush, this.charset, this.logger, this.fqcn,
                this.level, this.marker);
        } catch (final UnsupportedEncodingException e) {
            // this exception shouldn't really happen since we use Charset and not String
            throw new LoggingException(e);
        }
    }
