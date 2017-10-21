	public void setContentType(@Nullable MediaType mediaType) {
		if (mediaType != null) {
			Assert.isTrue(!mediaType.isWildcardType(), "'Content-Type' cannot contain wildcard type '*'");
			Assert.isTrue(!mediaType.isWildcardSubtype(), "'Content-Type' cannot contain wildcard subtype '*'");
			set(CONTENT_TYPE, mediaType.toString());
		}
		else {
			set(CONTENT_TYPE, null);
		}
	}
