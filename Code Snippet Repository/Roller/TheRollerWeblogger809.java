    public synchronized void purge(String[] patterns)
    {
        List<String> purgeList = new ArrayList<String>();
        for (Object objKey : cache.keySet()) {
            String key = (String) objKey;
            for (String s : patterns) {
                if (key.contains(s)) {
                    purgeList.add(key);
                    break;
                }
            }
        }
        for (String s: purgeList) {
            cache.remove(s);
        }
    }
