        public CidrInetRange(String pattern, InetAddress address, int cidr)
        {
            super(pattern);
            _raw = address.getAddress();
            _octets = cidr/8;
            _mask = 0xff&(0xff<<(8-cidr%8));
            _masked = _mask==0?0:_raw[_octets]&_mask;
                        
            if (cidr>(_raw.length*8))
                throw new IllegalArgumentException("CIDR too large: "+pattern);
                
            if (_mask!=0 && (0xff&_raw[_octets])!=_masked)
                throw new IllegalArgumentException("CIDR bits non zero: "+pattern);
            
            for (int o=_octets+(_mask==0?0:1);o<_raw.length;o++)
                if (_raw[o]!=0)
                    throw new IllegalArgumentException("CIDR bits non zero: "+pattern);
        }
