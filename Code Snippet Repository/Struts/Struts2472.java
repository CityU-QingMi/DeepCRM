    public DefaultClassFinder(List<Class> classes){
        this.classLoaderInterface = null;
        List<Info> infos = new ArrayList<>();
        List<Package> packages = new ArrayList<>();
        for (Class clazz : classes) {

            Package aPackage = clazz.getPackage();
            if (aPackage != null && !packages.contains(aPackage)){
                infos.add(new PackageInfo(aPackage));
                packages.add(aPackage);
            }

            ClassInfo classInfo = new ClassInfo(clazz, this);
            infos.add(classInfo);
            classInfos.put(classInfo.getName(), classInfo);
            for (Method method : clazz.getDeclaredMethods()) {
                infos.add(new MethodInfo(classInfo, method));
            }

            for (Constructor constructor : clazz.getConstructors()) {
                infos.add(new MethodInfo(classInfo, constructor));
            }

            for (Field field : clazz.getDeclaredFields()) {
                infos.add(new FieldInfo(classInfo, field));
            }
        }

        for (Info info : infos) {
            for (AnnotationInfo annotation : info.getAnnotations()) {
                List<Info> annotationInfos = getAnnotationInfos(annotation.getName());
                annotationInfos.add(info);
            }
        }
    }
