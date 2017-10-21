	@Override
	protected MutablePropertyValues parseSpecificContainerProperties(Element containerEle, ParserContext parserContext) {
		MutablePropertyValues properties = new MutablePropertyValues();

		if (containerEle.hasAttribute(RESOURCE_ADAPTER_ATTRIBUTE)) {
			String resourceAdapterBeanName = containerEle.getAttribute(RESOURCE_ADAPTER_ATTRIBUTE);
			if (!StringUtils.hasText(resourceAdapterBeanName)) {
				parserContext.getReaderContext().error(
						"Listener container 'resource-adapter' attribute contains empty value.", containerEle);
			}
			else {
				properties.add("resourceAdapter", new RuntimeBeanReference(resourceAdapterBeanName));
			}
		}

		String activationSpecFactoryBeanName = containerEle.getAttribute(ACTIVATION_SPEC_FACTORY_ATTRIBUTE);
		String destinationResolverBeanName = containerEle.getAttribute(DESTINATION_RESOLVER_ATTRIBUTE);
		if (StringUtils.hasText(activationSpecFactoryBeanName)) {
			if (StringUtils.hasText(destinationResolverBeanName)) {
				parserContext.getReaderContext().error("Specify either 'activation-spec-factory' or " +
						"'destination-resolver', not both. If you define a dedicated JmsActivationSpecFactory bean, " +
						"specify the custom DestinationResolver there (if possible).", containerEle);
			}
			properties.add("activationSpecFactory", new RuntimeBeanReference(activationSpecFactoryBeanName));
		}
		if (StringUtils.hasText(destinationResolverBeanName)) {
			properties.add("destinationResolver", new RuntimeBeanReference(destinationResolverBeanName));
		}

		String transactionManagerBeanName = containerEle.getAttribute(TRANSACTION_MANAGER_ATTRIBUTE);
		if (StringUtils.hasText(transactionManagerBeanName)) {
			properties.add("transactionManager", new RuntimeBeanReference(transactionManagerBeanName));
		}

		String phase = containerEle.getAttribute(PHASE_ATTRIBUTE);
		if (StringUtils.hasText(phase)) {
			properties.add("phase", phase);
		}

		return properties;
	}
