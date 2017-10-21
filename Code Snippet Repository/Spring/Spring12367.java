	protected String replaceUriTemplateParams(String uri, List<Param> params, Set<String> usedParams)
			throws JspException {

		String encoding = pageContext.getResponse().getCharacterEncoding();
		for (Param param : params) {
			String template = URL_TEMPLATE_DELIMITER_PREFIX + param.getName() + URL_TEMPLATE_DELIMITER_SUFFIX;
			if (uri.contains(template)) {
				usedParams.add(param.getName());
				String value = param.getValue();
				try {
					uri = uri.replace(template, (value != null ? UriUtils.encodePath(value, encoding) : ""));
				}
				catch (UnsupportedEncodingException ex) {
					throw new JspException(ex);
				}
			}
			else {
				template = URL_TEMPLATE_DELIMITER_PREFIX + '/' + param.getName() + URL_TEMPLATE_DELIMITER_SUFFIX;
				if (uri.contains(template)) {
					usedParams.add(param.getName());
					String value = param.getValue();
					try {
						uri = uri.replace(template,
								(value != null ? UriUtils.encodePathSegment(param.getValue(), encoding) : ""));
					}
					catch (UnsupportedEncodingException ex) {
						throw new JspException(ex);
					}
				}
			}
		}
		return uri;
	}
