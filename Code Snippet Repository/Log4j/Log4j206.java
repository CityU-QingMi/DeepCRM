    public static ClassLoader[] getClassLoaders() {
        List<ClassLoader> classLoaders = new ArrayList<>();
        ClassLoader tcl = getThreadContextClassLoader();
        classLoaders.add(tcl);
        ClassLoader current = LoaderUtil.class.getClassLoader();
        if (current != tcl) {
            classLoaders.add(current);
            ClassLoader parent = current.getParent();
            while (parent != null && !classLoaders.contains(parent)) {
                classLoaders.add(parent);
            }
        }
        ClassLoader parent = tcl;
        while (parent != null && !classLoaders.contains(parent)) {
            classLoaders.add(parent);
        }
        if (!classLoaders.contains(ClassLoader.getSystemClassLoader())) {
            classLoaders.add(ClassLoader.getSystemClassLoader());
        }
        return classLoaders.toArray(new ClassLoader[classLoaders.size()]);
    }
