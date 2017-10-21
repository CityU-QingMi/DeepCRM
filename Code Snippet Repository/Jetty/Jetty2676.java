        public boolean seen(int count)
        {
            synchronized (this)
            {
                if (count >= _seen.size())
                    return true;
                boolean s = _seen.get(count);
                _seen.set(count);
                return s;
            }
        }
