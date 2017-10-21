    @JsonCreator
    public ExtendedStackTraceElementMixIn(
            // @formatter:off
            @JsonProperty("class") final String declaringClass,
            @JsonProperty("method") final String methodName,
            @JsonProperty("file") final String fileName,
            @JsonProperty("line") final int lineNumber,
            @JsonProperty("exact") final boolean exact,
            @JsonProperty("location") final String location,
            @JsonProperty("version") final String version
            // @formatter:on
    ) {
        // empty
    }
