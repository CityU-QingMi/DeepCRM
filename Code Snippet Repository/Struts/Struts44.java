	public String execute() throws MalformedURLException, IOException {

		if (page != null) {

			InputStream in = ClassLoaderUtil.getResourceAsStream(page.substring(page.indexOf("//") + 1), getClass());
			page = page.replace("//", "/");

			if (in == null) {
				in = servletContext.getResourceAsStream(page);
				while (in == null && page.indexOf('/', 1) > 0) {
					page = page.substring(page.indexOf('/', 1));
					in = servletContext.getResourceAsStream(page);
				}
			}
			pageLines = read(in, -1);

			if (in != null) {
				in.close();
			}
		}

		if (className != null) {
			className = "/" + className.replace('.', '/') + ".java";
			InputStream in = getClass().getResourceAsStream(className);
			if (in == null) {
				in = servletContext.getResourceAsStream("/WEB-INF/src" + className);
			}
			classLines = read(in, -1);

			if (in != null) {
				in.close();
			}
		}

		String rootPath = ServletActionContext.getServletContext().getRealPath("/");

		if (config != null && (rootPath == null || config.startsWith(rootPath))) {
			int pos = config.lastIndexOf(':');
			configLine = Integer.parseInt(config.substring(pos + 1));
			config = config.substring(0, pos).replace("//", "/");
			configLines = read(new URL(config).openStream(), configLine);
		}
		return SUCCESS;
	}
