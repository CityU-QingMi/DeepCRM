	private ResponseEntity<Object> testException(Exception ex) {
		ResponseEntity<Object> responseEntity = this.exceptionHandlerSupport.handleException(ex, this.request);

		// SPR-9653
		if (HttpStatus.INTERNAL_SERVER_ERROR.equals(responseEntity.getStatusCode())) {
			assertSame(ex, this.servletRequest.getAttribute("javax.servlet.error.exception"));
		}

		this.defaultExceptionResolver.resolveException(this.servletRequest, this.servletResponse, null, ex);

		assertEquals(this.servletResponse.getStatus(), responseEntity.getStatusCode().value());

		return responseEntity;
	}
