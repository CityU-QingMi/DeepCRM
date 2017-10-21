	@Nullable
	public static Object resolveMultipartArgument(String name, MethodParameter parameter, HttpServletRequest request)
			throws Exception {

		MultipartHttpServletRequest multipartRequest =
				WebUtils.getNativeRequest(request, MultipartHttpServletRequest.class);
		boolean isMultipart = (multipartRequest != null || isMultipartContent(request));

		if (MultipartFile.class == parameter.getNestedParameterType()) {
			if (multipartRequest == null && isMultipart) {
				multipartRequest = new StandardMultipartHttpServletRequest(request);
			}
			return (multipartRequest != null ? multipartRequest.getFile(name) : null);
		}
		else if (isMultipartFileCollection(parameter)) {
			if (multipartRequest == null && isMultipart) {
				multipartRequest = new StandardMultipartHttpServletRequest(request);
			}
			return (multipartRequest != null ? multipartRequest.getFiles(name) : null);
		}
		else if (isMultipartFileArray(parameter)) {
			if (multipartRequest == null && isMultipart) {
				multipartRequest = new StandardMultipartHttpServletRequest(request);
			}
			if (multipartRequest != null) {
				List<MultipartFile> multipartFiles = multipartRequest.getFiles(name);
				return multipartFiles.toArray(new MultipartFile[multipartFiles.size()]);
			}
			else {
				return null;
			}
		}
		else if (Part.class == parameter.getNestedParameterType()) {
			return (isMultipart ? resolvePart(request, name) : null);
		}
		else if (isPartCollection(parameter)) {
			return (isMultipart ? resolvePartList(request, name) : null);
		}
		else if (isPartArray(parameter)) {
			return (isMultipart ? resolvePartArray(request, name) : null);
		}
		else {
			return UNRESOLVABLE;
		}
	}
