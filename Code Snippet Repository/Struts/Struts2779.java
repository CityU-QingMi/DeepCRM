    private void init() throws JasperException {

        if (!initialized) {
            processWebDotXml(ctxt);
            defaultJspProperty = new JspProperty(defaultIsXml,
                    defaultIsELIgnored,
                    defaultIsScriptingInvalid,
                    null, null, null, defaultDeferedSyntaxAllowedAsLiteral, 
                    defaultTrimDirectiveWhitespaces);
            initialized = true;
        }
    }
