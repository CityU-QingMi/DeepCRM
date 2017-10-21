    public void loadConversionProperties(String propsName, boolean require) {
        try {
            Iterator<URL> resources = ClassLoaderUtil.getResources(propsName, getClass(), true);
            while (resources.hasNext()) {
                URL url = resources.next();
                Properties props = new Properties();
                props.load(url.openStream());

                LOG.debug("Processing conversion file [{}]", propsName);

                for (Object o : props.entrySet()) {
                    Map.Entry entry = (Map.Entry) o;
                    String key = (String) entry.getKey();

                    try {
                        TypeConverter typeConverter = converterCreator.createTypeConverter((String) entry.getValue());
                        if (LOG.isDebugEnabled()) {
                            LOG.debug("\t{}:{} [treated as TypeConverter {}]", key, entry.getValue(), typeConverter);
                        }
                        converterHolder.addDefaultMapping(key, typeConverter);
                    } catch (Exception e) {
                        LOG.error("Conversion registration error", e);
                    }
                }
            }
        } catch (IOException ex) {
            if (require) {
                throw new XWorkException("Cannot load conversion properties file: "+propsName, ex);
            } else {
                LOG.debug("Cannot load conversion properties file: {}", propsName, ex);
            }
        }
    }
