    @Inject
    public void setContainer(Container container) throws ClassNotFoundException {
        Set<String> names = container.getInstanceNames(PropertyAccessor.class);
        for (String name : names) {
            Class cls = Class.forName(name);
            if (cls != null) {
                if (Map.class.isAssignableFrom(cls)) {
                    PropertyAccessor acc = container.getInstance(PropertyAccessor.class, name);
                }
                OgnlRuntime.setPropertyAccessor(cls, container.getInstance(PropertyAccessor.class, name));
                if (compoundRootAccessor == null && CompoundRoot.class.isAssignableFrom(cls)) {
                    compoundRootAccessor = (CompoundRootAccessor) container.getInstance(PropertyAccessor.class, name);
                }
            }
        }

        names = container.getInstanceNames(MethodAccessor.class);
        for (String name : names) {
            Class cls = Class.forName(name);
            if (cls != null) {
                OgnlRuntime.setMethodAccessor(cls, container.getInstance(MethodAccessor.class, name));
            }
        }

        names = container.getInstanceNames(NullHandler.class);
        for (String name : names) {
            Class cls = Class.forName(name);
            if (cls != null) {
                OgnlRuntime.setNullHandler(cls, new OgnlNullHandlerWrapper(container.getInstance(NullHandler.class, name)));
            }
        }
        if (compoundRootAccessor == null) {
            throw new IllegalStateException("Couldn't find the compound root accessor");
        }
        this.container = container;
    }
