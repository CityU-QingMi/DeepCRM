	public String execute() throws IOException {
		if (jsp != null) {
			//write it to file
			URL url = ServletActionContext.getServletContext().getResource(FILE);
			BufferedWriter writer = new BufferedWriter(new FileWriter(new File(url
					.getFile())));
			try {
				//directive
				writer.write("<%@ taglib prefix=\"s\" uri=\"/struts-tags\" %>");
				writer.write(jsp);
			} finally {
				if (writer != null)
					writer.close();
			}
		}
		return Action.SUCCESS;
	}
