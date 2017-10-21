    public MultiValueHashMap getReferencesToSchema(String schemaName) {

        MultiValueHashMap map          = new MultiValueHashMap();
        Iterator          mainIterator = referenceMap.keySet().iterator();

        while (mainIterator.hasNext()) {
            HsqlName name = (HsqlName) mainIterator.next();

            if (!name.schema.name.equals(schemaName)) {
                continue;
            }

            Iterator it = referenceMap.get(name);

            while (it.hasNext()) {
                map.put(name, it.next());
            }
        }

        return map;
    }
