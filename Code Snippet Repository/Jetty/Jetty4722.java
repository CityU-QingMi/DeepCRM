        public boolean hasNext()
        {
            getNext();
            if (_next < 0)
            {
                scratch = null;
                return false;
            }
            return true;
        }
