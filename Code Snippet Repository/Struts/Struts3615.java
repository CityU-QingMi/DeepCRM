    private void addPerActionPermission(ActionInvocation invocation, XStream stream) {
        Object action = invocation.getAction();
        if (action instanceof AllowedClasses) {
            Set<Class<?>> allowedClasses = ((AllowedClasses) action).allowedClasses();
            stream.addPermission(new ExplicitTypePermission(allowedClasses.toArray(new Class[allowedClasses.size()])));
        }
        if (action instanceof AllowedClassNames) {
            Set<String> allowedClassNames = ((AllowedClassNames) action).allowedClassNames();
            stream.addPermission(new ExplicitTypePermission(allowedClassNames.toArray(new String[allowedClassNames.size()])));
        }
        if (action instanceof XStreamPermissionProvider) {
            Collection<TypePermission> permissions = ((XStreamPermissionProvider) action).getTypePermissions();
            for (TypePermission permission : permissions) {
                stream.addPermission(permission);
            }
        }
    }
