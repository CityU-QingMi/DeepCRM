    @Override
    public Object build() {
        verify();
        // first try to use a builder class if one is available
        try {
            LOGGER.debug("Building Plugin[name={}, class={}].", pluginType.getElementName(),
                    pluginType.getPluginClass().getName());
            final Builder<?> builder = createBuilder(this.clazz);
            if (builder != null) {
                injectFields(builder);
                return builder.build();
            }
        } catch (final ConfigurationException e) { // LOG4J2-1908
            LOGGER.error("Could not create plugin of type {} for element {}", this.clazz, node.getName(), e);
            return null; // no point in trying the factory method
        } catch (final Exception e) {
            LOGGER.error("Could not create plugin of type {} for element {}: {}",
                    this.clazz, node.getName(),
                    (e instanceof InvocationTargetException ? ((InvocationTargetException) e).getCause() : e).toString(), e);
        }
        // or fall back to factory method if no builder class is available
        try {
            final Method factory = findFactoryMethod(this.clazz);
            final Object[] params = generateParameters(factory);
            return factory.invoke(null, params);
        } catch (final Exception e) {
            LOGGER.error("Unable to invoke factory method in {} for element {}: {}",
                    this.clazz, this.node.getName(),
                    (e instanceof InvocationTargetException ? ((InvocationTargetException) e).getCause() : e).toString(), e);
            return null;
        }
    }
