		@Override
		protected void doParse(Element element, ParserContext parserContext, BeanDefinitionBuilder builder) {
			String path = element.getAttribute("path");
			if (!StringUtils.hasText(path)) {
				parserContext.getReaderContext().error("Attribute 'path' must not be empty", element);
				return;
			}
			int dotIndex = path.indexOf(".");
			if (dotIndex == -1) {
				parserContext.getReaderContext().error(
						"Attribute 'path' must follow pattern 'beanName.propertyName'", element);
				return;
			}
			String beanName = path.substring(0, dotIndex);
			String propertyPath = path.substring(dotIndex + 1);
			builder.addPropertyValue("targetBeanName", beanName);
			builder.addPropertyValue("propertyPath", propertyPath);
		}
