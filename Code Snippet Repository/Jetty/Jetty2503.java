        @Override
        public int compare(Attribute o1, Attribute o2)
        {
            if( (o1.value == null) && (o2.value != null) )
            {
                return -1;
            }
            
            if( (o1.value != null) && (o2.value == null) )
            {
                return 1;
            }
            
            if( (o1.value == null) && (o2.value == null) )
            {
                return 0;
            }
            
            // Different lengths?
            int diff = o2.value.length() - o1.value.length();
            if(diff != 0)
            {
                return diff;
            }
            
            // Different names?
            diff = o2.value.compareTo(o1.value);
            if(diff != 0)
            {
                return diff;
            }
            
            // The paths are the same, base now on weight
            return o2.weight - o1.weight;
        }
