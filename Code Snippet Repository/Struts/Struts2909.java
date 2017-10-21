    private void parsePluginTags(Node parent) throws JasperException {
        reader.skipSpaces();

        if (reader.matches("<jsp:params")) {
            parseJspParams(parent);
            reader.skipSpaces();
        }

        if (reader.matches("<jsp:fallback")) {
            parseFallBack(parent);
            reader.skipSpaces();
        }
    }
