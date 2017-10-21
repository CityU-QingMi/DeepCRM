    public List<Class> findClasses(Test<ClassInfo> test) {
        classesNotLoaded.clear();
        List<Class> classes = new ArrayList<>();
        for (ClassInfo classInfo : classInfos.values()) {
            try {
                if (test.test(classInfo)) {
                    classes.add(classInfo.get());
                }
            } catch (Throwable e) {
                LOG.error("Error loading class [{}]", classInfo.getName(), e);
                classesNotLoaded.add(classInfo.getName());
            }
        }
        return classes;
    }
