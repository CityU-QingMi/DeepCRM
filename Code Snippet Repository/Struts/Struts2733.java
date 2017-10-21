    public static JavacErrorDetail createJavacError(String fname,
                                                    Node.Nodes page, StringBuffer errMsgBuf, int lineNum,
                                                    JspCompilationContext ctxt) throws JasperException {
        JavacErrorDetail javacError;
        // Attempt to map javac error line number to line in JSP page
        ErrorVisitor errVisitor = new ErrorVisitor(lineNum);
        page.visit(errVisitor);
        Node errNode = errVisitor.getJspSourceNode();
        if ((errNode != null) && (errNode.getStart() != null)) {
            // If this is a scriplet node then there is a one to one mapping
            // between JSP lines and Java lines
            if (errVisitor.getJspSourceNode() instanceof Node.Scriptlet) {
                javacError = new JavacErrorDetail(
                        fname,
                        lineNum,
                        errNode.getStart().getFile(),
                        errNode.getStart().getLineNumber() + lineNum -
                                errVisitor.getJspSourceNode().getBeginJavaLine(),
                        errMsgBuf,
                        ctxt);
            } else {
                javacError = new JavacErrorDetail(
                        fname,
                        lineNum,
                        errNode.getStart().getFile(),
                        errNode.getStart().getLineNumber(),
                        errMsgBuf,
                        ctxt);
            }
        } else {
/**/
/**/
/**/
/**/
/**/
/**/
/**/
/**/
/**/
/**/
/**/
/**/
/**/
            javacError = new JavacErrorDetail(
                    fname,
                    lineNum,
                    errMsgBuf);
        }
        return javacError;
    }
