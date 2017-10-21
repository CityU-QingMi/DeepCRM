    public String toString() {
        String ret = null;
        try {
            StringWriter sw = new StringWriter();
            doStartTag( new PrintWriter( sw, true ));
            // See, design precludes contents
            doEndTag( new PrintWriter( sw, true ));
            ret = sw.toString();
        } catch (Exception e) {
            ret = "Exception in tag";
            mLogger.error(ret,e);
        }
        return ret;
    }
