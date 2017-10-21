    private boolean parseAttribute(AttributesImpl attrs) throws JasperException {

        // Get the qualified name
        String qName = parseName();
        if (qName == null)
            return false;

        // Determine prefix and local name components
        String localName = qName;
        String uri = "";
        int index = qName.indexOf(':');
        if (index != -1) {
            String prefix = qName.substring(0, index);
            uri = pageInfo.getURI(prefix);
            if (uri == null) {
                err.jspError(reader.mark(),
                        "jsp.error.attribute.invalidPrefix", prefix);
            }
            localName = qName.substring(index + 1);
        }

        reader.skipSpaces();
        if (!reader.matches("="))
            err.jspError(reader.mark(), "jsp.error.attribute.noequal");

        reader.skipSpaces();
        char quote = (char) reader.nextChar();
        if (quote != '\'' && quote != '"')
            err.jspError(reader.mark(), "jsp.error.attribute.noquote");

        String watchString = "";
        if (reader.matches("<%="))
            watchString = "%>";
        watchString = watchString + quote;

        String attrValue = parseAttributeValue(watchString);
        attrs.addAttribute(uri, localName, qName, "CDATA", attrValue);
        return true;
    }
