    /* package */ static Map<String, Map<String, String>> getIncludePatternData()
    {
        Map<String, Map<String, String>> includePatternData = new HashMap<>();

        Map<String, String> data = new HashMap<>();
        data.put(REGEXP_PATTERN, "\\\\\\.");
        data.put(WILDCARD_PATTERN, "\\.");
        includePatternData.put(SPLIT_PATTERN, data);

        data = new HashMap<>();
        data.put(REGEXP_PATTERN, "\\.");
        data.put(WILDCARD_PATTERN, ".");
        includePatternData.put(JOIN_STRING, data);

        data = new HashMap<>();
        data.put(REGEXP_PATTERN, "\\[");
        data.put(WILDCARD_PATTERN, "[");
        includePatternData.put(ARRAY_BEGIN_STRING, data);

        data = new HashMap<>();
        data.put(REGEXP_PATTERN, "\\]");
        data.put(WILDCARD_PATTERN, "]");
        includePatternData.put(ARRAY_END_STRING, data);

        return includePatternData;
    }
