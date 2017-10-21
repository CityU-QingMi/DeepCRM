    private Map<String, String> parseParameters(String wwwAuthenticate)
    {
        Map<String, String> result = new HashMap<>();
        List<String> parts = splitParams(wwwAuthenticate);
        for (String part : parts)
        {
            Matcher matcher = PARAM_PATTERN.matcher(part);
            if (matcher.matches())
            {
                String name = matcher.group(1).trim().toLowerCase(Locale.ENGLISH);
                String value = matcher.group(2).trim();
                if (value.startsWith("\"") && value.endsWith("\""))
                    value = value.substring(1, value.length() - 1);
                result.put(name, value);
            }
        }
        return result;
    }
