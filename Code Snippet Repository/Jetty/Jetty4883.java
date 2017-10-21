        public IPAddrPattern(String value)
            throws IllegalArgumentException
        {
            if (value == null || value.trim().length() == 0)
                throw new IllegalArgumentException("Invalid IP address pattern: "+value);
                
            try
            {
                StringTokenizer parts = new StringTokenizer(value, ".");
                
                String part;
                for (int idx=0; idx<4; idx++)
                {
                    part = parts.hasMoreTokens() ? parts.nextToken().trim() : "0-255";
                    
                    int len = part.length();
                    if (len == 0 && parts.hasMoreTokens())
                        throw new IllegalArgumentException("Invalid IP address pattern: "+value);
                    
                    _octets[idx] = new OctetPattern(len==0 ? "0-255" : part);
                }
            }
            catch (IllegalArgumentException ex)
            {
                throw new IllegalArgumentException("Invalid IP address pattern: "+value, ex);
            }
        }
