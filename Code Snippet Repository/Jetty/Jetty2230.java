        public void release()
        {
            if (_resourceBaseIsCopy)
            {
                try
                {
                    File file = _resourceBase.getFile();
                    if (file!=null)
                        IO.delete(file);
                }
                catch(Exception e)
                {
                    __log.warn(e);
                }
            }
        }
