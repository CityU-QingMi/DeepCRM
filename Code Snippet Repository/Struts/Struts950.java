    protected Map unwrapParameters(Map params) {
        Map map = new HashMap(params.size());
        BeansWrapper objectWrapper = BeansWrapper.getDefaultInstance();
        for (Iterator iterator = params.entrySet().iterator(); iterator.hasNext();) {
            Map.Entry entry = (Map.Entry) iterator.next();

            Object value = entry.getValue();

            if (value != null) {
                // the value should ALWAYS be a descendant of TemplateModel
                if (value instanceof TemplateModel) {
                    try {
                        map.put(entry.getKey(), objectWrapper.unwrap((TemplateModel) value));
                    } catch (TemplateModelException e) {
                        LOG.error("failed to unwrap [{}] it will be ignored", value.toString(), e);
                    }
                }
                // if it doesn't, we'll do it the old way by just returning the toString() representation
                else {
                    map.put(entry.getKey(), value.toString());
                }
            }
        }
        return map;
    }
