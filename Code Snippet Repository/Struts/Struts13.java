	@After
	public void cleanUp() throws IOException {
		URL url = ServletActionContext.getServletContext().getResource(FILE);
		FileOutputStream out = new FileOutputStream(new File(url.getFile()));
		try {
			out.getChannel().truncate(0);
		} finally {
			if (out != null)
				out.close();
		}
	}
