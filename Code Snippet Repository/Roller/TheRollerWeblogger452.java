    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
    throws IOException, ServletException {
        if (mLogger.isDebugEnabled()) {
            mLogger.debug("Processing CharEncodingFilter");
        }
        try {
        	if (!"UTF-8".equals(req.getCharacterEncoding())) {
        		// only set encoding if not already UTF-8
        		// despite the fact that this is the first filter in the chain, on Glassfish it 
        		// is already too late to set request encoding without getting a WARN level log message
        		req.setCharacterEncoding("UTF-8");
        	}
            if (mLogger.isDebugEnabled()) {
                mLogger.debug("Set request character encoding to UTF-8");
            }
            
        } catch (UnsupportedEncodingException e) {
            // This should never happen since UTF-8 is a Java-specified required encoding.
            throw new ServletException("Can't set incoming encoding to UTF-8");
        }
        
        chain.doFilter(req, res);
    }
