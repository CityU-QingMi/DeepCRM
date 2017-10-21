    private void generateXmlProlog(Node.Nodes page) {

/**/
/**/
/**/
/**/
/**/
        String omitXmlDecl = pageInfo.getOmitXmlDecl();
        if ((omitXmlDecl != null && !JspUtil.booleanValue(omitXmlDecl))
                || (omitXmlDecl == null && page.getRoot().isXmlSyntax()
                        && !pageInfo.hasJspRoot() && !ctxt.isTagFile())) {
            String cType = pageInfo.getContentType();
            String charSet = cType.substring(cType.indexOf("charset=") + 8);
            out.printil("out.write(\"<?xml version=\\\"1.0\\\" encoding=\\\""
                    + charSet + "\\\"?>\\n\");");
        }

/**/
/**/
/**/
/**/
/**/

        String doctypeName = pageInfo.getDoctypeName();
        if (doctypeName != null) {
            String doctypePublic = pageInfo.getDoctypePublic();
            String doctypeSystem = pageInfo.getDoctypeSystem();
            out.printin("out.write(\"<!DOCTYPE ");
            out.print(doctypeName);
            if (doctypePublic == null) {
                out.print(" SYSTEM \\\"");
            } else {
                out.print(" PUBLIC \\\"");
                out.print(doctypePublic);
                out.print("\\\" \\\"");
            }
            out.print(doctypeSystem);
            out.println("\\\">\\n\");");
        }
    }
