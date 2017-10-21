        @Override
        public URLStreamHandler createURLStreamHandler(String protocol)
        {
            try
            {
                return getBuiltInHandler(protocol, loader);
            }
            catch (Exception e)
            {
                throw new RuntimeException("Unable to create URLStreamHandler for protocol [" + protocol + "]");
            }
        }
