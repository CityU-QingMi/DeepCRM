        public void visit(Node.TaglibDirective n) throws JasperException {
            JspUtil.checkAttributes("Taglib directive", n,
                    taglibDirectiveAttrs, err);
            // Either 'uri' or 'tagdir' attribute must be specified
            String uri = n.getAttributeValue("uri");
            String tagdir = n.getAttributeValue("tagdir");
            if (uri == null && tagdir == null) {
                err.jspError(n, "jsp.error.taglibDirective.missing.location");
            }
            if (uri != null && tagdir != null) {
                err
                        .jspError(n,
                                "jsp.error.taglibDirective.both_uri_and_tagdir");
            }
        }
