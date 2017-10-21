    @Override
    protected Class<?> findClass(String name) throws
            ClassNotFoundException {
        MemoryJavaFileObject fileObject = cachedObjects.get(name);
        if (fileObject != null) {
            byte[] bytes = fileObject.toByteArray();
            return defineClass(name, bytes, 0, bytes.length);
        }
        return super.findClass(name);
    }
