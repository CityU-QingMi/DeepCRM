    public LocationImpl(String description, String uri, int line, int column) {
        if (StringUtils.isEmpty(uri)) {
            this.uri = null;
            this.line = -1;
            this.column = -1;
        } else {
            this.uri = uri;
            this.line = line;
            this.column = column;
        }
        this.description = StringUtils.trimToNull(description);
    }
