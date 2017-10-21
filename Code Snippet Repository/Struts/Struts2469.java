    private void readClassDef(String className) {
        if (!className.endsWith(".class")) {
            className = className.replace('.', '/') + ".class";
        }
        try {
            URL resource = classLoaderInterface.getResource(className);
            if (resource != null) {
                try (InputStream in = resource.openStream()) {
                    ClassReader classReader = new ClassReader(in);
                    classReader.accept(new InfoBuildingVisitor(this), ClassReader.SKIP_DEBUG);
                }
            } else {
                throw new XWorkException("Could not load " + className);
            }
        } catch (IOException e) {
            throw new XWorkException("Could not load " + className, e);
        }

    }
