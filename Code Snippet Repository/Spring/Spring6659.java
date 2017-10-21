	protected void parseListenerConfiguration(Element ele, ParserContext parserContext, MutablePropertyValues configValues) {
		String destination = ele.getAttribute(DESTINATION_ATTRIBUTE);
		if (!StringUtils.hasText(destination)) {
			parserContext.getReaderContext().error(
					"Listener 'destination' attribute contains empty value.", ele);
		}
		configValues.add("destinationName", destination);

		if (ele.hasAttribute(SUBSCRIPTION_ATTRIBUTE)) {
			String subscription = ele.getAttribute(SUBSCRIPTION_ATTRIBUTE);
			if (!StringUtils.hasText(subscription)) {
				parserContext.getReaderContext().error(
						"Listener 'subscription' attribute contains empty value.", ele);
			}
			configValues.add("subscriptionName", subscription);
		}

		if (ele.hasAttribute(SELECTOR_ATTRIBUTE)) {
			String selector = ele.getAttribute(SELECTOR_ATTRIBUTE);
			if (!StringUtils.hasText(selector)) {
				parserContext.getReaderContext().error(
						"Listener 'selector' attribute contains empty value.", ele);
			}
			configValues.add("messageSelector", selector);
		}

		if (ele.hasAttribute(CONCURRENCY_ATTRIBUTE)) {
			String concurrency = ele.getAttribute(CONCURRENCY_ATTRIBUTE);
			if (!StringUtils.hasText(concurrency)) {
				parserContext.getReaderContext().error(
						"Listener 'concurrency' attribute contains empty value.", ele);
			}
			configValues.add("concurrency", concurrency);
		}
	}
