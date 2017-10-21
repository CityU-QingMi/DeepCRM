    public Object bean(Object aName) throws Exception {
        String name = aName.toString();
        Class c = classes.get(name);

        if (c == null) {
            c = ClassLoaderUtil.loadClass(name, StrutsUtil.class);
            classes.put(name, c);
        }

        return objectFactory.buildBean(c, stack.getContext());
    }
