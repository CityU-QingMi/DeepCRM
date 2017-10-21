        public boolean test(InetAddress item, byte[] raw)
        {
            if (raw.length!=_min.length)
                return false;

            boolean min_ok = false;
            boolean max_ok = false;

            for (int i=0;i<_min.length;i++)
            {
                int r = 0xff&raw[i];
                if (!min_ok)
                {
                    if (r<_min[i])
                        return false;
                    if (r>_min[i])
                        min_ok=true;
                }
                if (!max_ok)
                {
                    if (r>_max[i])
                        return false;
                    if (r<_max[i])
                        max_ok=true;
                }
                
                if (min_ok && max_ok)
                    break;
            }
            
            return true;
        }
