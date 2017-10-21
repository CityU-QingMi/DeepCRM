	public void setContentType(@Nullable MimeType mimeType) {
		if (mimeType != null) {
			Assert.isTrue(!mimeType.isWildcardType(), "'Content-Type' cannot contain wildcard type '*'");
			Assert.isTrue(!mimeType.isWildcardSubtype(), "'Content-Type' cannot contain wildcard subtype '*'");
			set(CONTENT_TYPE, mimeType.toString());
		}
		else {
			set(CONTENT_TYPE, null);
		}
	}
