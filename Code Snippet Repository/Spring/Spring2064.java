		@Nullable
		String merge(Element element, ReaderContext readerCtx) {
			String method = element.getAttribute(METHOD_ATTRIBUTE);
			if (StringUtils.hasText(method)) {
				return method.trim();
			}
			if (StringUtils.hasText(this.method)) {
				return this.method;
			}
			readerCtx.error("No method specified for " + element.getNodeName(), element);
			return null;
		}
