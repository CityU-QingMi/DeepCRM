    private IfFileName(final String glob, final String regex, final PathCondition[] nestedConditions) {
        if (regex == null && glob == null) {
            throw new IllegalArgumentException("Specify either a path glob or a regular expression. "
                    + "Both cannot be null.");
        }
        this.syntaxAndPattern = createSyntaxAndPatternString(glob, regex);
        this.pathMatcher = FileSystems.getDefault().getPathMatcher(syntaxAndPattern);
        this.nestedConditions = nestedConditions == null ? new PathCondition[0] : Arrays.copyOf(nestedConditions,
                nestedConditions.length);
    }
