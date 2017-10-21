        public int compare(Object obj1, Object obj2) {
            if (obj1 instanceof Locale && obj2 instanceof Locale) {
                Locale locale1 = (Locale)obj1;
                Locale locale2 = (Locale)obj2;
                int compName = locale1.getDisplayName().compareTo(locale2.getDisplayName());
                if (compName == 0) {
                    return locale1.toString().compareTo(locale2.toString());
                }
                return compName;
            }
            return 0;
        }
