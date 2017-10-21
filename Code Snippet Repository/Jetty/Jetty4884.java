        public boolean match(String value)
            throws IllegalArgumentException
        {
            if (value == null || value.trim().length() == 0)
                throw new IllegalArgumentException("Invalid IP address: "+value);
            
            try
            {
                StringTokenizer parts = new StringTokenizer(value, ".");
                
                boolean result = true;
                for (int idx=0; idx<4; idx++)
                {
                    if (!parts.hasMoreTokens())
                        throw new IllegalArgumentException("Invalid IP address: "+value);
                        
                    if (!(result &= _octets[idx].match(parts.nextToken())))
                        break;
                }
                return result;
            }
            catch (IllegalArgumentException ex)
            {
                throw new IllegalArgumentException("Invalid IP address: "+value, ex);
            }
        }
