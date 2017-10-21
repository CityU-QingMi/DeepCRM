        boolean isValid()
        {
            if (_lastModifiedValue==_resource.lastModified() && _contentLengthValue==_resource.length())
            {
                _lastAccessed=System.currentTimeMillis();
                return true;
            }

            if (this==_cache.remove(_key))
                invalidate();
            return false;
        }
