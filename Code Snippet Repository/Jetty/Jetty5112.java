    public static void dump(Appendable out, String indent, Collection<?>... collections) throws IOException
    {
        if (collections.length == 0)
            return;
        int size = 0;
        for (Collection<?> c : collections)
            size += c.size();
        if (size == 0)
            return;

        int i = 0;
        for (Collection<?> c : collections)
        {
            for (Object o : c)
            {
                i++;
                out.append(indent).append(" +- ");

                if (o instanceof Dumpable)
                    ((Dumpable)o).dump(out, indent + (i == size ? "    " : " |  "));
                else
                    dumpObject(out, o);
            }
        }
    }
