    private boolean parseJspAttributeAndBody(Node parent, String tag,
            String bodyType) throws JasperException {
        boolean result = false;

        if (reader.matchesOptionalSpacesFollowedBy("<jsp:attribute")) {
            // May be an EmptyBody, depending on whether
            // There's a "<jsp:body" before the ETag

            // First, parse <jsp:attribute> elements:
            parseNamedAttributes(parent);

            result = true;
        }

        if (reader.matchesOptionalSpacesFollowedBy("<jsp:body")) {
            // ActionBody
            parseJspBody(parent, bodyType);
            reader.skipSpaces();
            if (!reader.matchesETag(tag)) {
                err.jspError(reader.mark(), "jsp.error.unterminated", "&lt;"
                        + tag);
            }

            result = true;
        } else if (result && !reader.matchesETag(tag)) {
            // If we have <jsp:attribute> but something other than
            // <jsp:body> or the end tag, translation error.
            err.jspError(reader.mark(), "jsp.error.jspbody.required", "&lt;"
                    + tag);
        }

        return result;
    }
