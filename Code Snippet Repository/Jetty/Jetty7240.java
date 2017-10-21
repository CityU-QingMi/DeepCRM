        @Override
        public void add(int i, Object o)
        {
            if (_list == null)
                _list = new ArrayList<Object>();
            if (o instanceof String)
            {
                if (_lastString)
                {
                    int last = _list.size() - 1;
                    _list.set(last, (String) _list.get(last) + o);
                }
                else
                    _list.add(i, o);
                _lastString = true;
            }
            else
            {
                _lastString = false;
                _list.add(i, o);
            }
        }
