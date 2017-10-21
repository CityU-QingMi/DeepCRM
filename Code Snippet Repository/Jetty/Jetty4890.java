        public MinMaxInetRange(String pattern, InetAddress min, InetAddress max)
        {
            super(pattern);
            
            byte[] raw_min = min.getAddress();
            byte[] raw_max = max.getAddress();
            if (raw_min.length!=raw_max.length)
                throw new IllegalArgumentException("Cannot mix IPv4 and IPv6: "+pattern);
            
            if (raw_min.length==4)
            {
                // there must be 6 '.' or this is likely to be a legacy pattern
                int count=0;
                for (char c:pattern.toCharArray())
                    if (c=='.')
                        count++;
                if (count!=6)
                    throw new IllegalArgumentException("Legacy pattern: "+pattern);
            }
            
            _min = new int[raw_min.length];
            _max = new int[raw_min.length];
            
            for (int i=0;i<_min.length;i++)
            {
                _min[i]=0xff&raw_min[i];
                _max[i]=0xff&raw_max[i];
            }

            for (int i=0;i<_min.length;i++)
            {
                if (_min[i]>_max[i])
                    throw new IllegalArgumentException("min is greater than max: "+pattern);
                if (_min[i]<_max[i])
                    break;
            }
        }
