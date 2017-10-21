    public void javacError(JavacErrorDetail[] details) throws JasperException {
        
        if (details == null) {
            return;
        }
        
        Object[] args = null;
        StringBuffer buf = new StringBuffer();
        
        for (int i=0; i < details.length; i++) {
            if (details[i].getJspBeginLineNumber() >= 0) {
                args = new Object[] {
                        new Integer(details[i].getJspBeginLineNumber()), 
                        details[i].getJspFileName() };
                buf.append("\n\n");
                buf.append(Localizer.getMessage("jsp.error.single.line.number",
                        args));
                buf.append("\n");
                buf.append(details[i].getErrorMessage());
                buf.append("\n");
                buf.append(details[i].getJspExtract());
            } else {
                args = new Object[] {
                        new Integer(details[i].getJavaLineNumber()) };
                buf.append("\n\n");
                buf.append(Localizer.getMessage("jsp.error.java.line.number",
                        args));
                buf.append("\n");
                buf.append(details[i].getErrorMessage());
            }
        }
        buf.append("\n\nStacktrace:");
        throw new JasperException(
                Localizer.getMessage("jsp.error.unable.compile") + ": " + buf);
    }
