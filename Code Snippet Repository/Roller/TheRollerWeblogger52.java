    public static String expandProperties(String input, Map props) {
        
        if (input == null) {
            return null;
        }
        
        Matcher matcher = EXPANSION_PATTERN.matcher(input);

        StringBuffer expanded = new StringBuffer(input.length());
        while (matcher.find()) {
            String propName = matcher.group(2);
            String value = (String) props.get(propName);
            // if no value is found, use a value equal to the original expression
            if (value == null) {
                value = matcher.group(0);
            }
            // Fake a literal replacement since Matcher.quoteReplacement() is not present in 1.4.
            matcher.appendReplacement(expanded, "");
            expanded.append(value);
        }
        matcher.appendTail(expanded);
        
        return expanded.toString();
    }
