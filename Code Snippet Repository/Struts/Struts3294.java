    private static void processPattern(List<Pattern> results, Map<String, String> existingPatterns, String pattern, String type, Map<String, Map<String, String>> includePatternData) {
        // Compile a pattern for each *unique* "level" of the object
        // hierarchy specified in the regex.
        String[] patternPieces = pattern.split(includePatternData.get(SPLIT_PATTERN).get(type));

        String patternExpr = "";
        for (String patternPiece : patternPieces) {
            patternExpr = processPatternPiece(results, existingPatterns, patternExpr, patternPiece, type, includePatternData);
        }
    }
