    public Object buildBean(Class clazz, Map extraContext)
            throws Exception {
        try {
            return lookup(clazz.getName(), extraContext);
        }
        catch (Exception e) {
            if (extraContext != null) {
                String type = (String) extraContext.get(PLEXUS_COMPONENT_TYPE);

                if (type != null) {
                    return lookup(type, clazz.getName(), extraContext);
                }
            }

            throw e;
        }
    }
