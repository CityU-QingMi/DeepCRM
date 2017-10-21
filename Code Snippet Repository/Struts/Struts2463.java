    public List<Class> findClasses() {
        classesNotLoaded.clear();
        List<Class> classes = new ArrayList<>();
        for (ClassInfo classInfo : classInfos.values()) {
            try {
                classes.add(classInfo.get());
            } catch (Throwable e) {
                LOG.error("Error loading class [{}]", classInfo.getName(), e);
                classesNotLoaded.add(classInfo.getName());
            }
        }
        return classes;
    }
