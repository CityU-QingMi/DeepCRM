    private int count(String s,String sub)
    {
        int count=0;
        int index=s.indexOf(sub);

        while(index>=0)
        {
            count++;
            index=s.indexOf(sub,index+sub.length());
        }
        return count;
    }
