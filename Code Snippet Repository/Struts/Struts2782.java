        public JspProperty(String isXml, String elIgnored,
                String scriptingInvalid, String pageEncoding,
                Vector includePrelude, Vector includeCoda,
                String deferedSyntaxAllowedAsLiteral, 
                String trimDirectiveWhitespaces) {

            this.isXml = isXml;
            this.elIgnored = elIgnored;
            this.scriptingInvalid = scriptingInvalid;
            this.pageEncoding = pageEncoding;
            this.includePrelude = includePrelude;
            this.includeCoda = includeCoda;
            this.deferedSyntaxAllowedAsLiteral = deferedSyntaxAllowedAsLiteral;
            this.trimDirectiveWhitespaces = trimDirectiveWhitespaces;
        }
