    private static String toString(Object o)
    {
        if (o == null)
            return null;

        try
        {
            if (o.getClass().isArray())
            {
                StringBuffer sb = new StringBuffer();
                if (!o.getClass().getComponentType().isPrimitive())
                {
                    Object[] array= (Object[])o;
                    for (int i= 0; i < array.length; i++)
                    {
                        if (i > 0)
                            sb.append("\n");
                        sb.append(array.getClass().getComponentType().getName());
                        sb.append("[");
                        sb.append(i);
                        sb.append("]=");
                        sb.append(toString(array[i]));
                    }
                    return sb.toString();
                }
                else
                {
                    int length = Array.getLength(o);
                    for (int i=0;i<length;i++)
                    {
                        if (i > 0)
                            sb.append("\n");
                        sb.append(o.getClass().getComponentType().getName());
                        sb.append("[");
                        sb.append(i);
                        sb.append("]=");
                        sb.append(toString(Array.get(o, i)));
                    }
                    return sb.toString();
                }
            }
            else
                return o.toString();
        }
        catch (Exception e)
        {
            return e.toString();
        }
    }
