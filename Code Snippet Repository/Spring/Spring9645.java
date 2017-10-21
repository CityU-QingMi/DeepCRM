	@Override
	public boolean isFile() {
		try {
			URL url = this.servletContext.getResource(this.path);
			if (url != null && ResourceUtils.isFileURL(url)) {
				return true;
			}
			else {
				return (this.servletContext.getRealPath(this.path) != null);
			}
		}
		catch (MalformedURLException ex) {
			return false;
		}
	}
