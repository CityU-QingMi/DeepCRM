    protected <T> void getContainedBeans(Class<T> clazz, Collection<T> beans)
    {
        beans.addAll(getBeans(clazz));
        for (Container c : getBeans(Container.class))
        {
            Bean bean = getBean(c);
            if (bean!=null && bean.isManageable())
            {
                if (c instanceof ContainerLifeCycle)
                    ((ContainerLifeCycle)c).getContainedBeans(clazz, beans);
                else
                    beans.addAll(c.getContainedBeans(clazz));
            }
        }
    }
