    protected Object getConverter(Class clazz, String property) {
        LOG.debug("Retrieving convert for class [{}] and property [{}]", clazz, property);

        synchronized (clazz) {
            if ((property != null) && !converterHolder.containsNoMapping(clazz)) {
                try {
                    Map<String, Object> mapping = converterHolder.getMapping(clazz);

                    if (mapping == null) {
                        mapping = buildConverterMapping(clazz);
                    } else {
                        mapping = conditionalReload(clazz, mapping);
                    }

                    Object converter = mapping.get(property);
                    if (converter == null && LOG.isDebugEnabled()) {
                        LOG.debug("Converter is null for property [{}]. Mapping size [{}]:", property, mapping.size());
                        for (String next : mapping.keySet()) {
                            LOG.debug("{}:{}", next, mapping.get(next));
                        }
                    }
                    return converter;
                } catch (Throwable t) {
                    LOG.debug("Got exception trying to resolve convert for class [{}] and property [{}]", clazz, property, t);
                    converterHolder.addNoMapping(clazz);
                }
            }
        }
        return null;
    }
