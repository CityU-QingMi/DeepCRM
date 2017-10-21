        public Resource getResource(String path)
        {
            try
            {
                return getBaseResource().addPath(path);
            }
            catch(Exception e)
            {
                __log.warn(e);
            }
            return null;
        }
