    public static void removeStartsWith(String prefix, List<String> lines)
    {
        ListIterator<String> it = lines.listIterator();
        while (it.hasNext())
        {
            String line = it.next();
            if (line.startsWith(prefix))
            {
                it.remove();
            }
        }
    }
