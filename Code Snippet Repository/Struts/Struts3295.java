    private static String processPatternPiece(List<Pattern> results, Map<String, String> existingPatterns, String patternExpr, String patternPiece, String type, Map<String, Map<String, String>> includePatternData) {
        if (patternExpr.length() > 0) {
            patternExpr += includePatternData.get(JOIN_STRING).get(type);
        }
        patternExpr += patternPiece;

        // Check for duplicate patterns so that there is no overlap.
        if (!existingPatterns.containsKey(patternExpr)) {
            existingPatterns.put(patternExpr, patternExpr);
            if (isIndexedProperty(patternPiece, type, includePatternData)) {
                addPattern(results, patternExpr.substring(0, patternExpr.lastIndexOf(includePatternData.get(ARRAY_BEGIN_STRING).get(type))), type);
            }
            addPattern(results, patternExpr, type);
        }
        return patternExpr;
    }
