    public boolean nextProperty() throws IOException {
        String line = readProperty();

        if (line == null) {
            return false; // EOF
        }

        // parse the line
        String[] property = parseProperty(line);
        propertyName = unescapeJava(property[0]);
        propertyValue = unescapeJava(property[1], delimiter);
        return true;
    }
