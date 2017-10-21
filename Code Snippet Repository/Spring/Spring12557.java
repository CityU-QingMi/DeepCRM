		@Override
		public MultipartHttpServletRequest resolveMultipart(HttpServletRequest request) throws MultipartException {
			if (request.getAttribute("fail") != null) {
				throw new MaxUploadSizeExceededException(1000);
			}
			if (request instanceof MultipartHttpServletRequest) {
				throw new IllegalStateException("Already a multipart request");
			}
			if (request.getAttribute("resolved") != null) {
				throw new IllegalStateException("Already resolved");
			}
			request.setAttribute("resolved", Boolean.TRUE);
			return new AbstractMultipartHttpServletRequest(request) {
				@Override
				public HttpHeaders getMultipartHeaders(String paramOrFileName) {
					return null;
				}
				@Override
				public String getMultipartContentType(String paramOrFileName) {
					return null;
				}
			};
		}
