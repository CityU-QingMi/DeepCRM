    @Override
    public void setRequestURI(URI uri)
    {
        super.setRequestURI(uri);

        // parse parameter map
        Map<String, List<String>> pmap = new HashMap<>();

        String query = uri.getQuery();

        if (StringUtil.isNotBlank(query))
        {
            MultiMap<String> params = new MultiMap<String>();
            UrlEncoded.decodeTo(uri.getQuery(),params,StandardCharsets.UTF_8);

            for (String key : params.keySet())
            {
                List<String> values = params.getValues(key);
                if (values == null)
                {
                    pmap.put(key,new ArrayList<String>());
                }
                else
                {
                    // break link to original
                    List<String> copy = new ArrayList<>();
                    copy.addAll(values);
                    pmap.put(key,copy);
                }
            }

            super.setParameterMap(pmap);
        }
    }
