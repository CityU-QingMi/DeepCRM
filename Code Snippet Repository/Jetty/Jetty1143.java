        public void add(Entry entry)
        {
            if (_size==_entries.length)
            {
                Entry[] entries = new Entry[_entries.length+_growby];
                for (int i=0;i<_size;i++)
                {
                    int slot = (_offset+i)%_entries.length;
                    entries[i]=_entries[slot];
                    entries[i]._slot=i;
                }
                _entries=entries;
                _offset=0;
            }
            int slot=(_size++ + _offset)%_entries.length;
            _entries[slot]=entry;
            entry._slot=slot;
        }
