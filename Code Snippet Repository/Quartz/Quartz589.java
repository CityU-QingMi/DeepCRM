    public static <T extends Annotation> T getAnnotation(Class<?> clazz, Class<T> aClazz) {
        //Check class hierarchy
        for (Class<?> c = clazz; c != null; c = c.getSuperclass()) {
            T anno = c.getAnnotation(aClazz);
            if (anno != null) {
                return anno;
            }
        }

        //Check interfaces (breadth first)
        Queue<Class<?>> q = new LinkedList<Class<?>>();
        q.add(clazz);
        while (!q.isEmpty()) {
            Class<?> c = q.remove();
            if (c != null) {
                if (c.isInterface()) {
                    T anno = c.getAnnotation(aClazz);
                    if (anno != null) {
                        return anno;
                    }
                } else {
                    q.add(c.getSuperclass());
                }
                q.addAll(Arrays.asList(c.getInterfaces()));
            }
        }

        return null;
    }
