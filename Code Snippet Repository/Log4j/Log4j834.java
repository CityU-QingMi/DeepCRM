    @Override
    public boolean accept(final Path basePath, final Path relativePath, final BasicFileAttributes attrs) {
        final boolean result = pathMatcher.matches(relativePath);

        final String match = result ? "matches" : "does not match";
        final String accept = result ? "ACCEPTED" : "REJECTED";
        LOGGER.trace("IfFileName {}: '{}' {} relative path '{}'", accept, syntaxAndPattern, match, relativePath);
        if (result) {
            return IfAll.accept(nestedConditions, basePath, relativePath, attrs);
        }
        return result;
    }
