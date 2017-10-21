        public boolean eval(Object val) {
            myActualMap = (Map)val;
            boolean result = false;
            if(val != null) {
                if(myExpectedMap.size() == myActualMap.size()) {
                    Iterator keys = myExpectedMap.keySet().iterator();
                    boolean allSame = true;
                    while(keys.hasNext()) {
                        Object key = keys.next();
                        if(!myActualMap.containsKey(key)) {
                            allSame = false;
                            break;
                        }
                        else {
                            String[] expected = (String[])myExpectedMap.get(key);
                            String[] actual = (String[])myActualMap.get(key);
                            if(!Arrays.equals(expected, actual)) {
                                allSame = false;
                                break;
                            }
                        }
                    }
                    result = allSame;
                }
            }
            return result;
        }
