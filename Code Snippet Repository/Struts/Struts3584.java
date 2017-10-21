        private StringBuffer describeTo(Map map,StringBuffer sb) {
        	Iterator<String> it = map.keySet().iterator();
        	while(it.hasNext()) {
        		String key = it.next();
        		sb.append(key).append("=");
        		String[] value = (String[])map.get(key);
        		sb.append(value[0]);
        		if(it.hasNext()) {
        			sb.append(", ");
        		}
        	}
            return sb;
        }
