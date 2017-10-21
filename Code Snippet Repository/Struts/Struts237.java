        public MethodInjector(ContainerImpl container, Method method, String name) throws MissingDependencyException {
            this.method = method;
            if (!method.isAccessible()) {
                SecurityManager sm = System.getSecurityManager();
                try {
                    if (sm != null) {
                        sm.checkPermission(new ReflectPermission("suppressAccessChecks"));
                    }
                    method.setAccessible(true);
                } catch (AccessControlException e) {
                    throw new DependencyException("Security manager in use, could not access method: "
                            + name + "(" + method.getName() + ")", e);
                }
            }

            Class<?>[] parameterTypes = method.getParameterTypes();
            if (parameterTypes.length == 0) {
                throw new DependencyException(method + " has no parameters to inject.");
            }
            parameterInjectors = container.getParametersInjectors(
                    method, method.getParameterAnnotations(), parameterTypes, name);
        }
