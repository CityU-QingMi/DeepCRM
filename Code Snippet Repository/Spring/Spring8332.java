	private String getContent(MvcResult result) throws UnsupportedEncodingException {
		String content = result.getResponse().getContentAsString();
		if (StringUtils.hasLength(this.prefix)) {
			try {
				String reason = String.format("Expected a JSON payload prefixed with \"%s\" but found: %s",
						this.prefix, StringUtils.quote(content.substring(0, this.prefix.length())));
				MatcherAssert.assertThat(reason, content, StringStartsWith.startsWith(this.prefix));
				return content.substring(this.prefix.length());
			}
			catch (StringIndexOutOfBoundsException ex) {
				throw new AssertionError("JSON prefix \"" + this.prefix + "\" not found", ex);
			}
		}
		else {
			return content;
		}
	}
