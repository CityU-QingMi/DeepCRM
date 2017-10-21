    private void extractParams(String query)
    {
        if (query != null)
        {
            for (String nameValue : query.split("&"))
            {
                String[] parts = nameValue.split("=");
                if (parts.length > 0)
                {
                    String name = urlDecode(parts[0]);
                    if (name.trim().length() == 0)
                        continue;
                    param(name, parts.length < 2 ? "" : urlDecode(parts[1]), true);
                }
            }
        }
    }
