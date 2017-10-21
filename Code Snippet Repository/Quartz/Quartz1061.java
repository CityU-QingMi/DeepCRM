  public Set<String> getOrCreateTriggersGroupMap(String name) {
    ToolkitSet<String> set = triggersGroupSet.get(name);

    if (set != null && !set.isDestroyed()) {
      return set;
    } else {
      String nameForMap = generateName(TRIGGERS_GROUP_MAP_PREFIX + name);
      set = toolkit.getSet(nameForMap, String.class);
      triggersGroupSet.put(name, set);
      return set;
    }
  }
