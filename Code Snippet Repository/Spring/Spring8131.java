	@Override
	public void doFilter(ServletRequest request, ServletResponse response) throws ServletException, IOException {
		if (this.filter != null) {
			this.filter.doFilter(request, response, this.nextFilterChain);
		}
		else {
			Assert.state(this.servlet != null, "Neither a Filter not a Servlet set");
			this.servlet.service(request, response);
		}
	}
