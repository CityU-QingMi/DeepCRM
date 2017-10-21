        @Override
        public boolean matches(Path path)
        {
            try
            {
                return !Files.isHidden(path);
            }
            catch (IOException e)
            {
                StartLog.debug(e);
                return false;
            }
        }
