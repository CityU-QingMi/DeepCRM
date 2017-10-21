    public Action getAction() {
        return new ActionSupport() {

            public Map getMyMap() {
                Map _myMap = new LinkedHashMap();
                _myMap.put("england", "England");
                _myMap.put("america", "America");
                _myMap.put("australia", "Australia");
                _myMap.put("germany", "Germany");
                return _myMap;
            }

            public List getMySelectedMapIds() {
                List _mySelectedMapIds = new ArrayList();
                _mySelectedMapIds.add("america");
                _mySelectedMapIds.add("germany");
                return _mySelectedMapIds;
            }

            public List getMyAllSelectedMapIds() {
                List _mySelectedMapIds = new ArrayList();
                _mySelectedMapIds.add("england");
                _mySelectedMapIds.add("america");
                _mySelectedMapIds.add("australia");
                _mySelectedMapIds.add("germany");
                return _mySelectedMapIds;
            }
        };
    }
