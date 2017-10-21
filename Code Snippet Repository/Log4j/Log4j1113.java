    private boolean listContainsValue(final List<String> candidates, final StringBuilder toMatch) {
        if (toMatch == null) {
            for (int i = 0; i < candidates.size(); i++) {
                final String candidate = candidates.get(i);
                if (candidate == null) {
                    return true;
                }
            }
        } else {
            for (int i = 0; i < candidates.size(); i++) {
                final String candidate = candidates.get(i);
                if (candidate == null) {
                    return false;
                }
                if (StringBuilders.equals(candidate, 0, candidate.length(), toMatch, 0, toMatch.length())) {
                    return true;
                }
            }
        }
        return false;
    }
