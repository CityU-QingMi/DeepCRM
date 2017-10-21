    public void updateBeans(Object[] oldBeans, final Object[] newBeans)
    {
        // remove oldChildren not in newChildren
        if (oldBeans!=null)
        {
            loop: for (Object o:oldBeans)
            {
                if (newBeans!=null)
                {
                    for (Object n:newBeans)
                        if (o==n)
                            continue loop;
                }
                removeBean(o);
            }
        }

        // add new beans not in old
        if (newBeans!=null)
        {
            loop: for (Object n:newBeans)
            {
                if (oldBeans!=null)
                {
                    for (Object o:oldBeans)
                        if (o==n)
                            continue loop;
                }
                addBean(n);
            }
        }
    }
