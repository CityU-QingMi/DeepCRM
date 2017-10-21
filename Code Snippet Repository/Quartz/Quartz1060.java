  public Set<String> getOrCreateJobsGroupMap(String name) {
    ToolkitSet<String> set = jobsGroupSet.get(name);

    if (set != null && !set.isDestroyed()) {
      return set;
    } else {
      String nameForMap = generateName(JOBS_GROUP_MAP_PREFIX + name);
      set = toolkit.getSet(nameForMap, String.class);
      jobsGroupSet.put(name, set);
      return set;
    }
  }
