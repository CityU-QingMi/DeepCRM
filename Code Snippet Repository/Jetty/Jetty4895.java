        public boolean test(InetAddress item, byte[] raw)
        {
            if (raw.length!=4)
                return false;

            for (int i=0;i<4;i++)
                if ((0xff&raw[i])<_min[i] || (0xff&raw[i])>_max[i])
                    return false;
            
            return true;
        }
