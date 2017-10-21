        @Override
        public boolean equals(Object o)
        {
            if (o instanceof TimeNSize)
            {
                TimeNSize tns = (TimeNSize)o;
                return tns._lastModified==_lastModified && tns._size==_size;
            }
            return false;
        }
