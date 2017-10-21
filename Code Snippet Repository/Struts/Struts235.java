        public FieldInjector(ContainerImpl container, Field field, String name)
                throws MissingDependencyException {
            this.field = field;
            if (!field.isAccessible()) {
                SecurityManager sm = System.getSecurityManager();
                try {
                    if (sm != null) {
                        sm.checkPermission(new ReflectPermission("suppressAccessChecks"));
                    }
                    field.setAccessible(true);
                } catch (AccessControlException e) {
                    throw new DependencyException("Security manager in use, could not access field: "
                            + field.getDeclaringClass().getName() + "(" + field.getName() + ")", e);
                }
            }

            Key<?> key = Key.newInstance(field.getType(), name);
            factory = container.getFactory(key);
            if (factory == null) {
                throw new MissingDependencyException("No mapping found for dependency " + key + " in " + field + ".");
            }

            this.externalContext = ExternalContext.newInstance(field, key, container);
        }
