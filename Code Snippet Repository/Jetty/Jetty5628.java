    public static void main(String[] arg) throws Exception
    {
        String string = "Now \u0690xxxxxxxx";
        System.err.println(string);
        byte[] bytes=string.getBytes(StandardCharsets.UTF_8);
        System.err.println(new String(bytes));
        System.err.println(bytes.length);
        long calc=0;
        Utf8StringBuffer strbuf = new Utf8StringBuffer(bytes.length);
        for (int i=0;i<10;i++)
        {
            long s1=System.currentTimeMillis();
            for (int j=1000000; j-->0;)
            {
                calc+=new String(bytes,0,bytes.length,StandardCharsets.UTF_8).hashCode();
            }
            long s2=System.currentTimeMillis();
            for (int j=1000000; j-->0;)
            {
                calc+=StringUtil.toUTF8String(bytes,0,bytes.length).hashCode();
            }
            long s3=System.currentTimeMillis();
            for (int j=1000000; j-->0;)
            {
                Utf8StringBuffer buffer = new Utf8StringBuffer(bytes.length);
                buffer.append(bytes,0,bytes.length);
                calc+=buffer.toString().hashCode();
            }
            long s4=System.currentTimeMillis();
            for (int j=1000000; j-->0;)
            {
                strbuf.reset();
                strbuf.append(bytes,0,bytes.length);
                calc+=strbuf.toString().hashCode();
            }
            long s5=System.currentTimeMillis();

            System.err.println((s2-s1)+", "+(s3-s2)+", "+(s4-s3)+", "+(s5-s4));
        }
        System.err.println(calc);
    }
