    public JspServletWrapper(ServletContext servletContext,
			     Options options,
			     String tagFilePath,
			     TagInfo tagInfo,
			     JspRuntimeContext rctxt,
			     URL tagFileJarUrl)
	    throws JasperException {

	this.isTagFile = true;
        this.config = null;	// not used
        this.options = options;
	this.jspUri = tagFilePath;
	this.tripCount = 0;
        ctxt = new JspCompilationContext(jspUri, tagInfo, options,
					 servletContext, this, rctxt,
					 tagFileJarUrl);
    }
