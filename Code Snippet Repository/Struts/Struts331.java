    public Object getPropertyThroughIteration(Map context, Collection collection, String property, Object key)
            throws OgnlException {
        //TODO
        for (Object currTest : collection) {
            if (_accessor.getProperty(context, currTest, property).equals(key)) {
                return currTest;
            }
        }
        //none found
        return null;
    }
