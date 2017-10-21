    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
        throws IOException, ServletException {
        HttpServletRequest httpReq = (HttpServletRequest) request;

		SaltCache saltCache = SaltCache.getInstance();
        String salt = RandomStringUtils.random(20, 0, 0, true, true, null, new SecureRandom());
        saltCache.put(salt, Boolean.TRUE);
        httpReq.setAttribute("salt", salt);

        chain.doFilter(request, response);
    }
