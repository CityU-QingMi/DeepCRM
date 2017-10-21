        public String toString()
        {
            StringBuilder result = new StringBuilder();
           
            result.append(_desc);
            result.append('=');
            result.append(_value);
            
            return result.toString();
        }
