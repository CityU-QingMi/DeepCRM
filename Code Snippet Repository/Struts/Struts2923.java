    private String parseAttributeValue(String watch) throws JasperException {
        Mark start = reader.mark();
        Mark stop = reader.skipUntilIgnoreEsc(watch);
        if (stop == null) {
            err.jspError(start, "jsp.error.attribute.unterminated", watch);
        }

        String ret = parseQuoted(start, reader.getText(start, stop),
                watch.charAt(watch.length() - 1));
        if (watch.length() == 1) // quote
            return ret;

        // putback delimiter '<%=' and '%>', since they are needed if the
        // attribute does not allow RTexpression.
        return "<%=" + ret + "%>";
    }
