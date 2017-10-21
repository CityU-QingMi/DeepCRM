    @Override
    public String toString()
    {
        String[] vhosts = getVirtualHosts();

        StringBuilder b = new StringBuilder();

        Package pkg = getClass().getPackage();
        if (pkg != null)
        {
            String p = pkg.getName();
            if (p != null && p.length() > 0)
            {
                String[] ss = p.split("\\.");
                for (String s : ss)
                    b.append(s.charAt(0)).append('.');
            }
        }
        b.append(getClass().getSimpleName()).append('@').append(Integer.toString(hashCode(),16));
        b.append('{').append(getContextPath()).append(',').append(getBaseResource()).append(',').append(_availability);

        if (vhosts != null && vhosts.length > 0)
            b.append(',').append(vhosts[0]);
        b.append('}');

        return b.toString();
    }
