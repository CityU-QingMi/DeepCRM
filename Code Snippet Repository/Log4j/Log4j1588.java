        static Iso8601_Rule getRule(final int tokenLen) {
            switch(tokenLen) {
            case 1:
                return Iso8601_Rule.ISO8601_HOURS;
            case 2:
                return Iso8601_Rule.ISO8601_HOURS_MINUTES;
            case 3:
                return Iso8601_Rule.ISO8601_HOURS_COLON_MINUTES;
            default:
                throw new IllegalArgumentException("invalid number of X");                    
            }
        }        
