        public <T> T getInstance(Class<T> type) {
            try {
                if (type == FileManagerFactory.class) {
                    return (T) fileManagerFactory;
                }
                T obj;
                if (type == ObjectFactory.class) {
                    obj = type.getConstructor(Container.class).newInstance(this);
                    OgnlReflectionProvider rp = new OgnlReflectionProvider() {

                        @Override
                        public void setProperties(Map<String, ?> properties, Object o) {
                        }

                        public void setProperties(Map<String, ?> properties, Object o, Map<String, Object> context, boolean throwPropertyExceptions) throws ReflectionException {
                            if (o instanceof ActionChainResult) {
                                ((ActionChainResult) o).setActionName(String.valueOf(properties.get("actionName")));
                            }
                        }

                        @Override
                        public void setProperty(String name, Object value, Object o, Map<String, Object> context, boolean throwPropertyExceptions) {
                            if (o instanceof ActionChainResult) {
                                ((ActionChainResult) o).setActionName((String) value);
                            }
                        }
                    };
                    DefaultInterceptorFactory dif = new DefaultInterceptorFactory();
                    dif.setObjectFactory((ObjectFactory) obj);
                    dif.setReflectionProvider(rp);

                    DefaultResultFactory drf = new DefaultResultFactory();
                    drf.setObjectFactory((ObjectFactory) obj);
                    drf.setReflectionProvider(rp);

                    ((ObjectFactory) obj).setInterceptorFactory(dif);
                    ((ObjectFactory) obj).setResultFactory(drf);
                } else {
                    obj = type.newInstance();
                }
                return obj;
            } catch (Exception e) {
               throw new RuntimeException(e);
            }
        }
