    private Map retrieveSetMap() {
        Map setMap;
        Object topObj = peek();
        if (shouldUseOldMap(topObj)) {
            setMap = (Map) topObj;
        } else {
            setMap = new HashMap();
            setMap.put(MAP_IDENTIFIER_KEY, "");
            push(setMap);
        }
        return setMap;
    }
