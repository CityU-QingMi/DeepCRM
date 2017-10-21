        @Override
        public Map<String, List<String>> getHeaders()
        {
            Map<String, List<String>> headersMap = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
            HttpFields fields = getHttpFields();
            for(String name: fields.getFieldNamesCollection())
            {
                headersMap.put(name,fields.getValuesList(name));
            }
            return headersMap;
        }
