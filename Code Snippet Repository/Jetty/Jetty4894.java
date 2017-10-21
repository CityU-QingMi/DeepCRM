        public LegacyInetRange(String pattern)
        {
            super(pattern);
            
            String[] parts = pattern.split("\\.");
            if (parts.length!=4)
                throw new IllegalArgumentException("Bad legacy pattern: "+pattern);
            
            for (int i=0;i<4;i++)
            {
                String part=parts[i].trim();
                int dash = part.indexOf('-');
                if (dash<0)
                    _min[i]=_max[i]=Integer.parseInt(part);
                else 
                {
                    _min[i] = (dash==0)?0:StringUtil.toInt(part,0);
                    _max[i] = (dash==part.length()-1)?255:StringUtil.toInt(part,dash+1);
                }
                
                if (_min[i]<0 || _min[i]>_max[i] || _max[i]>255)
                    throw new IllegalArgumentException("Bad legacy pattern: "+pattern);
            }
        }
