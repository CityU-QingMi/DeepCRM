    @SuppressWarnings("")
    public static Method[] listSMDMethods(Class clazz, boolean ignoreInterfaces) {
        final List<Method> methods = new LinkedList<>();
        if (ignoreInterfaces) {
            for (Method method : clazz.getMethods()) {
                SMDMethod smdMethodAnnotation = method.getAnnotation(SMDMethod.class);
                if (smdMethodAnnotation != null) {
                    methods.add(method);
                }
            }
        } else {
            // recurse the entire superclass/interface hierarchy and add in
            // order encountered
            JSONUtil.visitInterfaces(clazz, new JSONUtil.ClassVisitor() {
                public boolean visit(Class aClass) {
                    for (Method method : aClass.getMethods()) {
                        SMDMethod smdMethodAnnotation = method.getAnnotation(SMDMethod.class);
                        if ((smdMethodAnnotation != null) && !methods.contains(method)) {
                            methods.add(method);
                        }
                    }
                    return true;
                }
            });
        }

        Method[] methodResult = new Method[methods.size()];
        return methods.toArray(methodResult);
    }
