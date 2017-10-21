        @Override
        public boolean delete(String id) throws Exception
        {
            if (id.equals(unloadableId))
            {
                o = null;
                return true;
            }
            return false;
        }
