    public String render(final WeblogEntryComment comment, String text) {
        StringBuilder result;
        result = new StringBuilder();
        
        if (text != null) {
            Matcher matcher;
            matcher = PATTERN.matcher(text);

            int start = 0;
            int end = text.length();
            
            while (start < end) {
                if (matcher.find()) {
                    // Copy up to the match
                    result.append(text.substring(start, (matcher.start())));

                    // Copy the URL and create the hyperlink
                    // Unescape HTML as we don't know if that setting is on
                    String url;
                    url = Utilities.unescapeHTML(text.substring(
                            matcher.start(), matcher.end()));

                    // Build the anchor tag and escape HTML in the URL output
                    result.append("<a href=\"");
                    result.append(Utilities.escapeHTML(url));
                    result.append("\">");
                    result.append(Utilities.escapeHTML(url));
                    result.append("</a>");

                    // Increment the starting index
                    start = matcher.end();
                }
                else {
                    // Copy the remainder
                    result.append(text.substring(start, end));

                    // Increment the starting index to exit the loop
                    start = end;
                }
            }
        }

        return result.toString();
    }
