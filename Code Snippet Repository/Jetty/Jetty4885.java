        public boolean match(String value)
            throws IllegalArgumentException
        {
            if (value == null || value.trim().length() == 0)
                throw new IllegalArgumentException("Invalid octet: "+value);

            try
            {
                int number = Integer.parseInt(value);
                return match(number);
            }
            catch (NumberFormatException ex)
            {
                throw new IllegalArgumentException("Invalid octet: "+value);
            }
        }
