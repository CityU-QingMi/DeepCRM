        public void addBefore(@Name("beforeClass") String beforeClass,@Name("configClass")String... configClass)
        {
            if (configClass!=null && beforeClass!=null)
            {
                ListIterator<String> iter = listIterator();
                while (iter.hasNext())
                {
                    String cc=iter.next();
                    if (beforeClass.equals(cc))
                    {
                        iter.previous();
                        for (int i=0;i<configClass.length;i++)
                            iter.add(configClass[i]);
                        return;
                    }
                }
            }
            throw new IllegalArgumentException("beforeClass '"+beforeClass+"' not found in "+this);
        }
