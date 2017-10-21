    protected void addDefaultPermissions(ActionInvocation invocation, XStream stream) {
        stream.addPermission(new ExplicitTypePermission(new Class[]{invocation.getAction().getClass()}));
        if (invocation.getAction() instanceof ModelDriven) {
            stream.addPermission(new ExplicitTypePermission(new Class[]{((ModelDriven) invocation.getAction()).getModel().getClass()}));
        }
        stream.addPermission(NullPermission.NULL);
        stream.addPermission(PrimitiveTypePermission.PRIMITIVES);
        stream.addPermission(ArrayTypePermission.ARRAYS);
        stream.addPermission(CollectionTypePermission.COLLECTIONS);
        stream.addPermission(new ExplicitTypePermission(new Class[]{Date.class}));
    }
