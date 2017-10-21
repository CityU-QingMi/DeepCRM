        private URLStreamHandler getBuiltInHandler(String protocol, ClassLoader classLoader)
        {
            URLStreamHandler handler = handlers.get(protocol);
            
            if (handler == null)
            {
                for (String prefix : STREAM_HANDLER_PREFIXES)
                {
                    String className = prefix + '.' + protocol + ".Handler";
                    try
                    {
                        Class<?> clazz = Class.forName(className, false, classLoader);
                        handler = (URLStreamHandler) clazz.newInstance();
                        break;
                    }
                    catch (Exception ignore)
                    {
                        ignore.printStackTrace(System.err);
                    }
                }
                
                if (handler != null)
                {
                    handlers.put(protocol, handler);
                }
            }
            
            if (handler == null)
            {
                throw new RuntimeException("Unable to find handler for protocol [" + protocol + "]");
            }
            
            return handler;
        }
