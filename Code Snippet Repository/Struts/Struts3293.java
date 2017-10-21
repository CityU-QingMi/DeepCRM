    /* package */ static List<Pattern> processIncludePatterns(Set<String> includePatterns, String type, Map<String, Map<String, String>> includePatternData) {
        if (includePatterns != null) {
            List<Pattern> results = new ArrayList<>(includePatterns.size());
            Map<String, String> existingPatterns = new HashMap<>();
            for (String pattern : includePatterns) {
                processPattern(results, existingPatterns, pattern, type, includePatternData);
            }
            return results;
        } else {
            return null;
        }
    }
