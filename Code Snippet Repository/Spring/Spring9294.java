	private String generateForm(MultiValueMap<String, String> form, Charset charset) {
		StringBuilder builder = new StringBuilder();
		try {
			for (Iterator<String> names = form.keySet().iterator(); names.hasNext();) {
				String name = names.next();
				for (Iterator<String> values = form.get(name).iterator(); values.hasNext();) {
					String value = values.next();
					builder.append(URLEncoder.encode(name, charset.name()));
					if (value != null) {
						builder.append('=');
						builder.append(URLEncoder.encode(value, charset.name()));
						if (values.hasNext()) {
							builder.append('&');
						}
					}
				}
				if (names.hasNext()) {
					builder.append('&');
				}
			}
		}
		catch (UnsupportedEncodingException ex) {
			throw new IllegalStateException(ex);
		}
		return builder.toString();
	}
