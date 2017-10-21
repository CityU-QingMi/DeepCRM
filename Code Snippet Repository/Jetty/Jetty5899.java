        public void addAfter(@Name("afterClass") String afterClass,@Name("configClass")String... configClass)
        {
            if (configClass!=null && afterClass!=null)
            {
                ListIterator<String> iter = listIterator();
                while (iter.hasNext())
                {
                    String cc=iter.next();
                    if (afterClass.equals(cc))
                    {
                        for (int i=0;i<configClass.length;i++)
                            iter.add(configClass[i]);
                        return;
                    }
                }
            }
            throw new IllegalArgumentException("afterClass '"+afterClass+"' not found in "+this);
        }
