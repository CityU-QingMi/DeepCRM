	private ServletWriter setupContextWriter(OutputStream os)
			throws FileNotFoundException, JasperException {
		ServletWriter writer;
		// Setup the ServletWriter
		String javaEncoding = ctxt.getOptions().getJavaEncoding();
		OutputStreamWriter osw = null;

		try {
		    osw = new OutputStreamWriter(os, javaEncoding);
		} catch (UnsupportedEncodingException ex) {
		    errDispatcher.jspError("jsp.error.needAlternateJavaEncoding",
		            javaEncoding);
		}

		writer = new ServletWriter(new PrintWriter(osw));
		ctxt.setWriter(writer);
		return writer;
	}
