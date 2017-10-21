    @Override
    public void dump(PrintWriter out) throws IOException
    {
        out.printf("request is %s%n",request == null ? "NULL" : "PRESENT");

        if (request != null)
        {
            Map<String, String[]> params = request.getParameterMap();
            List<String> paramNames = new ArrayList<>();
            paramNames.addAll(params.keySet());
            Collections.sort(paramNames);

            out.printf("parameters.size = [%d]%n",params.size());

            for (String name : paramNames)
            {
                out.printf(" param[%s] = [",name);
                boolean delim = false;
                for (String val : params.get(name))
                {
                    if (delim)
                    {
                        out.print(", ");
                    }
                    out.print(val);
                    delim = true;
                }
                out.println("]");
            }
        }
    }
