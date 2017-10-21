        public DefaultImpl()
        {
            ClassLoader cl = Thread.currentThread().getContextClassLoader();
            try
            {
                Class<?> mgmtFactory = Class.forName("java.lang.management.ManagementFactory",true,cl);
                Class<?> runtimeClass = Class.forName("java.lang.management.RuntimeMXBean",true,cl);
                Class<?> noparams[] = new Class<?>[0];
                Method mxBeanMethod = mgmtFactory.getMethod("getRuntimeMXBean",noparams);
                if (mxBeanMethod == null)
                {
                    throw new UnsupportedOperationException("method getRuntimeMXBean() not found");
                }
                mxBean = mxBeanMethod.invoke(mgmtFactory);
                if (mxBean == null)
                {
                    throw new UnsupportedOperationException("getRuntimeMXBean() method returned null");
                }
                uptimeMethod = runtimeClass.getMethod("getUptime",noparams);
                if (mxBean == null)
                {
                    throw new UnsupportedOperationException("method getUptime() not found");
                }
            }
            catch (ClassNotFoundException | 
                   NoClassDefFoundError | 
                   NoSuchMethodException | 
                   SecurityException | 
                   IllegalAccessException | 
                   IllegalArgumentException | 
                   InvocationTargetException e)
            {
                throw new UnsupportedOperationException("Implementation not available in this environment",e);
            }
        }
