	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
			FilterChain filterChain) throws ServletException, IOException {

		if (this.removeOnly) {
			ForwardedHeaderRemovingRequest theRequest = new ForwardedHeaderRemovingRequest(request);
			filterChain.doFilter(theRequest, response);
		}
		else {
			HttpServletRequest theRequest = new ForwardedHeaderExtractingRequest(request, this.pathHelper);
			HttpServletResponse theResponse = (this.relativeRedirects ?
					RelativeRedirectResponseWrapper.wrapIfNecessary(response, HttpStatus.SEE_OTHER) :
					new ForwardedHeaderExtractingResponse(response, theRequest));
			filterChain.doFilter(theRequest, theResponse);
		}
	}
