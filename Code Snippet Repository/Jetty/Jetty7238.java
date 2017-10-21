        public String getPath()
        {
            if (_path == null)
            {
                if (getParent() != null && getParent().getTag() != null)
                    _path = getParent().getPath() + "/" + _tag;
                else
                    _path = "/" + _tag;
            }
            return _path;
        }
