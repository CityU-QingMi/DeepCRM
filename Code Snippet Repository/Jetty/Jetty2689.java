    String getAuthSchemeFromHeader(String header)
    {
        // No header provided, return the empty string
        if (header == null || header.isEmpty())
        {
            return "";
        }
        // Trim any leading whitespace
        String trimmed_header = header.trim();
        // Find the first space, all characters prior should be the auth_scheme
        int index = trimmed_header.indexOf(' ');
        if (index > 0) {
            return trimmed_header.substring(0, index);
        }
        // If we don't find a space, this is likely malformed, just return the entire value
        return trimmed_header;
    }
