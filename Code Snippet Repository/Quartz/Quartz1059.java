  protected ToolkitStore<?, ?> toolkitMap(String nameOfMap) {
    ToolkitStore<?, ?> map = toolkitMaps.get(nameOfMap);
    if (map != null && !map.isDestroyed()) {
      return map;
    } else {
      map = createStore(nameOfMap);
      toolkitMaps.put(nameOfMap, map);
      return map;
    }
  }
