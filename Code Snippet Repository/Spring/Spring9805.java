	public RequestPartServletServerHttpRequest(HttpServletRequest request, String partName)
			throws MissingServletRequestPartException {

		super(request);

		this.multipartRequest = MultipartResolutionDelegate.asMultipartHttpServletRequest(request);
		this.partName = partName;

		HttpHeaders headers = this.multipartRequest.getMultipartHeaders(this.partName);
		if (headers == null) {
			throw new MissingServletRequestPartException(partName);
		}
		this.headers = headers;
	}
