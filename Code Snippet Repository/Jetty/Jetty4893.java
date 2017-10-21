        public boolean test(InetAddress item, byte[] raw)
        {
            if (raw.length!=_raw.length)
                return false;
            
            for (int o=0;o<_octets;o++)
                if (_raw[o]!=raw[o])
                    return false;
            
            if (_mask!=0 && (raw[_octets]&_mask)!=_masked)
                return false;
            return true;
        }
