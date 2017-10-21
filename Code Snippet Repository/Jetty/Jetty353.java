        public boolean matches(Origin origin)
        {
            if (getAddress().equals(origin.getAddress()))
                return false;

            boolean result = included.isEmpty();
            Origin.Address address = origin.getAddress();
            for (String included : this.included)
            {
                if (matches(address, included))
                {
                    result = true;
                    break;
                }
            }
            for (String excluded : this.excluded)
            {
                if (matches(address, excluded))
                {
                    result = false;
                    break;
                }
            }
            return result;
        }
