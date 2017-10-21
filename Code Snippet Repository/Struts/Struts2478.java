    public List<Class> findClassesInPackage(String packageName, boolean recursive) {
        classesNotLoaded.clear();
        List<Class> classes = new ArrayList<>();
        for (ClassInfo classInfo : classInfos.values()) {
            try {
                if (recursive && classInfo.getPackageName().startsWith(packageName)){
                    classes.add(classInfo.get());
                } else if (classInfo.getPackageName().equals(packageName)){
                    classes.add(classInfo.get());
                }
            } catch (Throwable e) {
                LOG.error("Error loading class [{}]", classInfo.getName(), e);
                classesNotLoaded.add(classInfo.getName());
            }
        }
        return classes;
    }
