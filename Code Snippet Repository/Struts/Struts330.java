    private Map getSetMap(Map context, Collection collection, String property) throws OgnlException {
        LOG.trace("getting set Map");

        String path = ReflectionContextState.getCurrentPropertyPath(context);
        Map map = ReflectionContextState.getSetMap(context, path);

        if (map == null) {
            LOG.trace("creating set Map");

            map = new HashMap();
            map.put(null, new SurrugateList(collection));
            for (Object currTest : collection) {
                Object currKey = _accessor.getProperty(context, currTest, property);
                if (currKey != null) {
                    map.put(currKey, currTest);
                }
            }
            ReflectionContextState.setSetMap(context, map, path);
        }
        return map;
    }
