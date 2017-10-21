	@Override
	public void setServletContext(ServletContext servletContext) {
		if (this.attributes != null) {
			for (Map.Entry<String, Object> entry : this.attributes.entrySet()) {
				String attributeName = entry.getKey();
				if (logger.isWarnEnabled()) {
					if (servletContext.getAttribute(attributeName) != null) {
						logger.warn("Replacing existing ServletContext attribute with name '" + attributeName + "'");
					}
				}
				servletContext.setAttribute(attributeName, entry.getValue());
				if (logger.isInfoEnabled()) {
					logger.info("Exported ServletContext attribute with name '" + attributeName + "'");
				}
			}
		}
	}
