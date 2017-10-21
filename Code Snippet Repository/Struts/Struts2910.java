    private void parseStandardAction(Node parent) throws JasperException {
        Mark start = reader.mark();

        if (reader.matches(INCLUDE_ACTION)) {
            parseInclude(parent);
        } else if (reader.matches(FORWARD_ACTION)) {
            parseForward(parent);
        } else if (reader.matches(INVOKE_ACTION)) {
            if (!isTagFile) {
                err.jspError(reader.mark(), "jsp.error.action.isnottagfile",
                        "&lt;jsp:invoke");
            }
            parseInvoke(parent);
        } else if (reader.matches(DOBODY_ACTION)) {
            if (!isTagFile) {
                err.jspError(reader.mark(), "jsp.error.action.isnottagfile",
                        "&lt;jsp:doBody");
            }
            parseDoBody(parent);
        } else if (reader.matches(GET_PROPERTY_ACTION)) {
            parseGetProperty(parent);
        } else if (reader.matches(SET_PROPERTY_ACTION)) {
            parseSetProperty(parent);
        } else if (reader.matches(USE_BEAN_ACTION)) {
            parseUseBean(parent);
        } else if (reader.matches(PLUGIN_ACTION)) {
            parsePlugin(parent);
        } else if (reader.matches(ELEMENT_ACTION)) {
            parseElement(parent);
        } else if (reader.matches(ATTRIBUTE_ACTION)) {
            err.jspError(start, "jsp.error.namedAttribute.invalidUse");
        } else if (reader.matches(BODY_ACTION)) {
            err.jspError(start, "jsp.error.jspbody.invalidUse");
        } else if (reader.matches(FALLBACK_ACTION)) {
            err.jspError(start, "jsp.error.fallback.invalidUse");
        } else if (reader.matches(PARAMS_ACTION)) {
            err.jspError(start, "jsp.error.params.invalidUse");
        } else if (reader.matches(PARAM_ACTION)) {
            err.jspError(start, "jsp.error.param.invalidUse");
        } else if (reader.matches(OUTPUT_ACTION)) {
            err.jspError(start, "jsp.error.jspoutput.invalidUse");
        } else {
            err.jspError(start, "jsp.error.badStandardAction");
        }
    }
