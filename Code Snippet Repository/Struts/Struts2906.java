    private void parseEmptyBody(Node parent, String tag) throws JasperException {
        if (reader.matches("/>")) {
            // Done
        } else if (reader.matches(">")) {
            if (reader.matchesETag(tag)) {
                // Done
            } else if (reader.matchesOptionalSpacesFollowedBy("<jsp:attribute")) {
                // Parse the one or more named attribute nodes
                parseNamedAttributes(parent);
                if (!reader.matchesETag(tag)) {
                    // Body not allowed
                    err.jspError(reader.mark(),
                            "jsp.error.jspbody.emptybody.only", "&lt;" + tag);
                }
            } else {
                err.jspError(reader.mark(), "jsp.error.jspbody.emptybody.only",
                        "&lt;" + tag);
            }
        } else {
            err.jspError(reader.mark(), "jsp.error.unterminated", "&lt;" + tag);
        }
    }
