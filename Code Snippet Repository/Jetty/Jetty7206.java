        public static WebSocketServletFactory load(ServletContext ctx, WebSocketPolicy policy)
        {
            try
            {
                Class<? extends WebSocketServletFactory> wsClazz =
                        (Class<? extends WebSocketServletFactory>) Class.forName(DEFAULT_IMPL,true,Thread.currentThread().getContextClassLoader());
                Constructor<? extends WebSocketServletFactory> ctor = wsClazz.getDeclaredConstructor(new Class<?>[]{ServletContext.class, WebSocketPolicy.class});
                return ctor.newInstance(ctx, policy);
            }
            catch (ClassNotFoundException e)
            {
                throw new RuntimeException("Unable to load " + DEFAULT_IMPL, e);
            }
            catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e)
            {
                throw new RuntimeException("Unable to instantiate " + DEFAULT_IMPL, e);
            }
        }
