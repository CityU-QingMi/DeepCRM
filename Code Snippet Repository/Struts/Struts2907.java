    private void parseOptionalBody(Node parent, String tag, String bodyType)
            throws JasperException {
        if (reader.matches("/>")) {
            // EmptyBody
            return;
        }

        if (!reader.matches(">")) {
            err.jspError(reader.mark(), "jsp.error.unterminated", "&lt;" + tag);
        }

        if (reader.matchesETag(tag)) {
            // EmptyBody
            return;
        }

        if (!parseJspAttributeAndBody(parent, tag, bodyType)) {
            // Must be ( '>' # Body ETag )
            parseBody(parent, tag, bodyType);
        }
    }
