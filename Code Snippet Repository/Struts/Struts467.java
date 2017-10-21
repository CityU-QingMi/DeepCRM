    public static Attributes addLocationAttributes(Locator locator, Attributes attrs) {
        if (locator == null || attrs.getIndex(URI, SRC_ATTR) != -1) {
            // No location information known, or already has it
            return attrs;
        }
        
        // Get an AttributeImpl so that we can add new attributes.
        AttributesImpl newAttrs = attrs instanceof AttributesImpl ?
            (AttributesImpl)attrs : new AttributesImpl(attrs);

        newAttrs.addAttribute(URI, SRC_ATTR, Q_SRC_ATTR, "CDATA", locator.getSystemId());
        newAttrs.addAttribute(URI, LINE_ATTR, Q_LINE_ATTR, "CDATA", Integer.toString(locator.getLineNumber()));
        newAttrs.addAttribute(URI, COL_ATTR, Q_COL_ATTR, "CDATA", Integer.toString(locator.getColumnNumber()));
        
        return newAttrs;
    }
