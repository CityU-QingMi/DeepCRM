        public void inject(InternalContext context, Object o) {
            ExternalContext<?> previous = context.getExternalContext();
            context.setExternalContext(externalContext);
            try {
                field.set(o, factory.create(context));
            } catch (IllegalAccessException e) {
                throw new AssertionError(e);
            } finally {
                context.setExternalContext(previous);
            }
        }
